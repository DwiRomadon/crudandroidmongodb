package com.dwiromadon.myapplication.pembeli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.dwiromadon.myapplication.R;
import com.dwiromadon.myapplication.server.BaseURL;
import com.squareup.picasso.Picasso;

public class DetailPembeli extends AppCompatActivity {

    EditText edtKodeBuku, edtJudulBuku, edtPengarang, edtPenerbit, edtTahunTerbit, edtHarga;
    ImageView imgGambarBuku;

    String strKodeBuku, strJudulBuku, strPengarang, strPenerbit, strTahunTerbit, strHarga, strGamabr, _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pembeli);

        edtKodeBuku = (EditText) findViewById(R.id.edtKodeBuku);
        edtJudulBuku = (EditText) findViewById(R.id.edtJudulBuku);
        edtPenerbit = (EditText) findViewById(R.id.edtPenerbit);
        edtPengarang = (EditText) findViewById(R.id.edtPengarang);
        edtTahunTerbit = (EditText) findViewById(R.id.edtTahunTerbit);
        edtHarga = (EditText) findViewById(R.id.edtHargaBuku);

        imgGambarBuku = (ImageView) findViewById(R.id.gambar);

        Intent i = getIntent();
        strKodeBuku = i.getStringExtra("kodeBuku");
        strJudulBuku = i.getStringExtra("judulBuku");
        strPengarang = i.getStringExtra("pengarang");
        strPenerbit = i.getStringExtra("penerbit");
        strTahunTerbit = i.getStringExtra("tahunTerbit");
        strHarga = i.getStringExtra("hargaBuku");
        strGamabr = i.getStringExtra("gambar");
        _id = i.getStringExtra("_id");

        edtKodeBuku.setText(strKodeBuku);
        edtJudulBuku.setText(strJudulBuku);
        edtPenerbit.setText(strPenerbit);
        edtPengarang.setText(strPengarang);
        edtTahunTerbit.setText(strTahunTerbit);
        edtHarga.setText(strHarga);
        Picasso.get().load(BaseURL.baseUrl + "gambar/" + strGamabr)
                .into(imgGambarBuku);
    }
}
