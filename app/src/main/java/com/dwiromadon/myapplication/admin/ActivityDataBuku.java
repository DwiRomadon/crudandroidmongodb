package com.dwiromadon.myapplication.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dwiromadon.myapplication.R;
import com.dwiromadon.myapplication.adapter.Adapterbuku;
import com.dwiromadon.myapplication.model.ModelBuku;
import com.dwiromadon.myapplication.server.BaseURL;
import com.dwiromadon.myapplication.users.LoginActivity;
import com.dwiromadon.myapplication.users.RegistrasiActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActivityDataBuku extends AppCompatActivity {

    ProgressDialog pDialog;

    Adapterbuku adapter;
    ListView list;

    ArrayList<ModelBuku> newsList = new ArrayList<ModelBuku>();
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_buku);
        getSupportActionBar().setTitle("Data Buku");
        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        list = (ListView) findViewById(R.id.array_list);
        newsList.clear();
        adapter = new Adapterbuku(ActivityDataBuku.this, newsList);
        list.setAdapter(adapter);
        getAllBuku();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(ActivityDataBuku.this, HomeAdminActivity.class);
        startActivity(i);
        finish();
    }

    private void getAllBuku() {
        // Pass second argument as "null" for GET requests
        pDialog.setMessage("Loading");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, BaseURL.dataBuku, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            if (status == false) {
                                Log.d("data buku = ", response.toString());
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    final ModelBuku buku = new ModelBuku();
                                    final String _id = jsonObject.getString("_id");
                                    final String judulBuku = jsonObject.getString("judulBuku");
                                    final String kodeBuku = jsonObject.getString("kodeBuku");
                                    final String pengarng = jsonObject.getString("pengarang");
                                    final String penerbit = jsonObject.getString("penerbit");
                                    final String hargaBuku = jsonObject.getString("hargaBuku");
                                    final String tahunTerbit = jsonObject.getString("tahunTerbit");
                                    final String gamabr = jsonObject.getString("gambar");
                                    buku.setKodeBuku(kodeBuku);
                                    buku.setJudulBuku(judulBuku);
                                    buku.setPengarang(pengarng);
                                    buku.setPenerbit(penerbit);
                                    buku.setHargaBuku(hargaBuku);
                                    buku.setTahunTerbit(tahunTerbit);
                                    buku.setGambar(gamabr);
                                    buku.set_id(_id);

                                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            // TODO Auto-generated method stub
                                            Intent a = new Intent(ActivityDataBuku.this, EditBukuDanHapusActivity.class);
                                            a.putExtra("kodeBuku", newsList.get(position).getKodeBuku());
                                            a.putExtra("_id", newsList.get(position).get_id());
                                            a.putExtra("judulBuku", newsList.get(position).getJudulBuku());
                                            a.putExtra("pengarang", newsList.get(position).getPengarang());
                                            a.putExtra("penerbit", newsList.get(position).getPenerbit());
                                            a.putExtra("hargaBuku", newsList.get(position).getHargaBuku());
                                            a.putExtra("tahunTerbit", newsList.get(position).getTahunTerbit());
                                            a.putExtra("gambar", newsList.get(position).getGambar());
                                            startActivity(a);
                                        }
                                    });
                                    newsList.add(buku);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });

        /* Add your Requests to the RequestQueue to execute */
        mRequestQueue.add(req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
