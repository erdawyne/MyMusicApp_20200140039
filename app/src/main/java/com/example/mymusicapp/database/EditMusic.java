package com.example.mymusicapp.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mymusicapp.R;

import org.json.JSONException;
import org.json.JSONObject;

public class EditMusic extends AppCompatActivity {

    TextView editmusik;
    EditText ednama;
    Button btedit;
    String id,namamusik;
    int sukses;

    private static String url_update = "";
    private static final String TAG = EditMusic.class.getSimpleName();
    private  static final String TAG_SUCCES = "succes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_music);

        editmusik = findViewById(R.id.editmusik);
        ednama = findViewById(R.id.ednama);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("musik1");
        namamusik = bundle.getString("musik2");

        editmusik.setText("editmusik : " + id);
        ednama.setText(namamusik);
        btedit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditMusic();
            }
        });
    }
    public void EditMusic()
    {
        namamusik = ednama.getText().toString();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringReq = new StringRequest(Request.Method.POST,url_update, new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                Log.d(TAG,"Respon : " + response.toString());
            }
        });try{
        JSONObject jObj = new JSONObject(response);
        sukses = jObj.getInt(TAG_SUCCES);
        if (sukses == 1){
            Toast.makeText(EditMusic.this, "Sukses merename musik", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(EditMusic.this, "Gagal", Toast.LENGTH_SHORT).show();
        }
    }catch (JSONException e){
            e.printStackTrace();
    } }
}, new Response.ErrorListener(){
    @Override
    public void onErrorResponse(VolleyError error){
        Log.e(TAG,"Error : "+error.getMessage());
        Toast.makeText(EditMusic.this,"Gagal Edit Rename",Toast.LENGTH_SHORT).show();
        }
        })
        {
@Override
protected Map<String, String> getParams(){
        Map<String, String>params=new HasMap<>();
        params.put("id",id);
        params.put("namamusik",ednama);

        return params;
        }
        };
        });
