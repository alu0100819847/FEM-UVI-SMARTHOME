package com.example.smarthome;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.smarthome.fcube.commands.FCColor;
import com.example.smarthome.fcube.commands.FCOff;
import com.example.smarthome.fcube.commands.FCOn;
import com.example.smarthome.fcube.config.FeedbackCubeConfig;
import com.example.smarthome.fcube.config.FeedbackCubeManager;
import com.example.smarthome.models.Uvi;
import com.google.firebase.auth.FirebaseAuth;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener  {

    private static final String API_BASE_URL = "https://api.openweathermap.org";
    final private HistoricalUvi hU = new HistoricalUvi();
    private static final String LOG_TAG = "MiW";
    private final String API_KEY = "f7a1ec680558bf51478ca958f2b7bdda";
    private ListView lvRespuesta;
    private List<String[]> location;
    private Context context = this;

    private ArrayList<String[]> responseData;

    private IUviRESTAPIService apiService;
    private FirebaseAuth mFirebaseAuth;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAuth = FirebaseAuth.getInstance();
        this.counter = 0;
        startApp();
    }

    private void startApp(){
        lvRespuesta =(ListView) findViewById(R.id.lvRespuesta);
        lvRespuesta.setOnItemClickListener(this);
        this.responseData = new ArrayList<>();
        this.location = new Locations(this).getData();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(IUviRESTAPIService.class);
        for(String[] info : location){
            if(info != null){
                this.obtenerInfoUvi(info);
            }
        }
    }

    public void mostrar(){
        LocationsAdapter locationsAdapter = new LocationsAdapter(context, R.layout.location_adapter, this.responseData);
        lvRespuesta.setAdapter(locationsAdapter);
    }

    public void obtenerInfoUvi(final String[] data) {
        Call<Uvi> call_async = apiService.getUvi(data[1], data[2], this.API_KEY);
        final String loc = data[0];
        Log.i(LOG_TAG, call_async.request().toString());
        call_async.enqueue(new Callback<Uvi>() {

            @Override
            public void onResponse(Call<Uvi> call, Response<Uvi> response) {
                Uvi uviResponse = response.body();
                if (null != uviResponse) {
                    String[] responseString = {loc, String.valueOf(uviResponse.getValue())};
                    Log.i(LOG_TAG, "obtenerInfoPais => respuesta=" + uviResponse.toString());
                    responseData.add(responseString);
                    List<String> historical = new ArrayList<>();
                    historical.add(loc.replace(" ","-"));
                    historical.add(uviResponse.getDate_iso());
                    historical.add(String.valueOf(uviResponse.getValue()));
                    hU.add(historical);
                    counter++;
                    if(counter > 4){
                        mostrar();
                    }
                } else {
                    //tvRespuesta.setText(getString(R.string.strError));
                    Log.i(LOG_TAG, getString(R.string.strError));
                }
            }


            @Override
            public void onFailure(Call<Uvi> call, Throwable t) {
                Toast.makeText(
                        getApplicationContext(),
                        "ERROR: " + t.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
                Log.i(LOG_TAG, "onFailure");
                Log.e(LOG_TAG, t.getMessage());
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id ){
        Log.i(LOG_TAG, "" + position);
        float val = new Float(responseData.get(position)[1]);
        FCOn fco = new FCOn(FeedbackCubeConfig
                .getSingleInstance().getIp());
        new FeedbackCubeManager().execute(fco);
        if( val < 2.00){
            FCColor fcc = new FCColor(FeedbackCubeConfig
                    .getSingleInstance().getIp(), "" + 0, ""
                    + 128, "" + 0);
            new FeedbackCubeManager().execute(fcc);

        } else if(val < 5.00){
            FCColor fcc = new FCColor(FeedbackCubeConfig
                    .getSingleInstance().getIp(), "" + 255, ""
                    + 255, "" + 1);
            new FeedbackCubeManager().execute(fcc);
        } else if(val < 7.00){
            FCColor fcc = new FCColor(FeedbackCubeConfig
                    .getSingleInstance().getIp(), "" + 255, ""
                    + 165, "" + 2);
            new FeedbackCubeManager().execute(fcc);
        } else if(val < 10.00){
            FCColor fcc = new FCColor(FeedbackCubeConfig
                    .getSingleInstance().getIp(), "" + 255, ""
                    + 1, "" + 0);
            new FeedbackCubeManager().execute(fcc);
        } else {
            FCColor fcc = new FCColor(FeedbackCubeConfig
                    .getSingleInstance().getIp(), "" + 128, ""
                    + 0, "" + 128);
            new FeedbackCubeManager().execute(fcc);
        }
        Bundle bundle = new Bundle();
        bundle.putString(getString(R.string.key), responseData.get(position)[0]);
        Intent intent = new Intent(getApplicationContext(), HistoricalUvi.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void logout(){
        mFirebaseAuth.signOut();
        Log.i(LOG_TAG, "Loged out");
        finish();
    }

    private void turnOff(){
        FCOff fcoff = new FCOff(FeedbackCubeConfig
                .getSingleInstance().getIp());
        new FeedbackCubeManager().execute(fcoff);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opcTurnOff:
                this.turnOff();
                return true;
            case R.id.opcHistorial:
                Bundle bundle = new Bundle();
                bundle.putString(getString(R.string.key), "");

                Intent intent = new Intent(getApplicationContext(), HistoricalUvi.class);
                intent.putExtras(bundle);
                startActivity(intent);

                return true;
            case R.id.opcLogOut:
                this.logout();
                return true;

        }
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}
