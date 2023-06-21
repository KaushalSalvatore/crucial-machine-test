package com.crucialtechnologies.task.mapfragment;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.crucialtechnologies.task.R;
import com.crucialtechnologies.task.mapfragment.projectHelper.Config;
import com.crucialtechnologies.task.mapfragment.projectHelper.DataParser;
import com.crucialtechnologies.task.mapfragment.projectHelper.LocationUpdateListener;
import com.crucialtechnologies.task.mapfragment.projectHelper.MyApplication;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;




public class FragmentMap extends Fragment implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener,
           com.google.android.gms.location.LocationListener, android.location.LocationListener,GoogleApiClient.ConnectionCallbacks, GoogleMap.OnMyLocationButtonClickListener{
    private static GoogleMapOptions options = new GoogleMapOptions()
            .mapType(GoogleMap.MAP_TYPE_NORMAL)
            .compassEnabled(true)
            .rotateGesturesEnabled(true)
            .tiltGesturesEnabled(true)
            .zoomControlsEnabled(true)
            .scrollGesturesEnabled(true)
            .mapToolbarEnabled(true);

    private GoogleMap mMap;
    private static SupportMapFragment myMapFragment = SupportMapFragment.newInstance(options);
    private FragmentManager myFragmentManager;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    private Handler mHandler = new Handler();

    private Location LastLocation;
    private double lats = 0.0;
    private double longs = 0.0;

    private double latsDes = 22.7290114;
    private double longsDes = 75.8881497;


//22.7290114,75.8881497
    private static final int LOCATION_SOURCE = 0;
    private static final int LOCATION_DESTINATION = 1;
    private static final int LOCATION_SOURCE_DESTINATION = 2;
    String latitude,longitude;
    LocationManager mLocationManager;

    String version;
      boolean placeSearch=true;
    final int AUTOCOMPLETE_REQUEST_SOURCE = 1;
    final int AUTOCOMPLETE_REQUEST_DES = 2;
    private FusedLocationProviderClient mFusedLocationClient;
    protected boolean hasLocationPermissions;
    private LocationCallback locationCallback;
    protected static final int REQUEST_PERMISSIONS_LOCATION = 2;
    protected boolean isLocationServiceEnableRequestShown;
    private ArrayList<LocationUpdateListener> locationUpdateListeners = new ArrayList<>();
    private boolean isRequestingLocationUpdates;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map_view, container, false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        myFragmentManager = getChildFragmentManager();
        myMapFragment = (SupportMapFragment) myFragmentManager.findFragmentById(R.id.fragment_map);
        intView( v);
        if (MyApplication.getInstance().getGoogleApiClient() == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(MyApplication.getInstance().getApplicationContext())
                    .addApi(LocationServices.API)
                    .enableAutoManage(getActivity(), this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();

            MyApplication.getInstance().setGoogleApiClient(mGoogleApiClient);
        } else {
            mGoogleApiClient = MyApplication.getInstance().getGoogleApiClient();
        }
        try {
            PackageInfo pInfo = getContext().getPackageManager().getPackageInfo( getContext().getPackageName(), 0);
            version = pInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        mLocationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        myMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(getContext(),
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                       //buildGoogleApiClient();
                        mMap.setMyLocationEnabled(true);
                    }
                }
                else {
                   // buildGoogleApiClient();
                    mMap.setMyLocationEnabled(true);
                }
               // setupMapOnCameraChange();
            }
        });

          if (!mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            showGpsDisabledDialog();
        }

        LocationUpdateListener locationUpdateListener = new LocationUpdateListener() {
            @Override
            public void onLocationUpdated(Location location) {
                Config.getInstance().setCurrentLatitude("" + location.getLatitude());
                Config.getInstance().setCurrentLongitude("" + location.getLongitude());
                String SourceLoaction=lats+"";
                String DesLoaction=latsDes+"";
                if (mMap != null&&placeSearch) {
                    LastLocation = location;
                    latitude=LastLocation.getLatitude()+"";
                    longitude=LastLocation.getLongitude()+"";
                    if (!DesLoaction.equals("0.0"))
                    {
                        onPlotLocation(true,LOCATION_SOURCE_DESTINATION,location.getLatitude(),location.getLongitude(),AUTOCOMPLETE_REQUEST_SOURCE);
                    }
                    else {
                        displayLocation();
                    }
                    getAddress(location.getLatitude(), location.getLongitude(),AUTOCOMPLETE_REQUEST_SOURCE);


                }

            }
        };
        addLocationUpdateListener(locationUpdateListener);

        locationCallback = new LocationCallback() {

            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    setLocationUpdate(location);
                }
            }
        };

        return  v;

    }
   void addLocationUpdateListener(LocationUpdateListener listener) {
        isRequestingLocationUpdates = true;
        if (locationUpdateListeners == null) {
            locationUpdateListeners = new ArrayList<>();
        }
        locationUpdateListeners.add(listener);
    }

    public  void intView(View v)
  {
      mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
  }
    void getCurrentLocation() {
        setUpLocationClientIfNeeded();
        if (!mGoogleApiClient.isConnected()) {
            mGoogleApiClient.connect();
        }

        if (ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (!checkForLocationPermissions())
                getLocationPermissions();
        } else {

            if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {

                if (LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient) != null) {
                    Config.getInstance().setCurrentLatitude(""
                            + LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient).getLatitude());
                    Config.getInstance().setCurrentLongitude(""
                            + LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient).getLongitude());
//                    getLocationName();
                }
            }

            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                // Logic to handle location object
                                setLocationUpdate(location);
                            }
                        }
                    });

            mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                    locationCallback, null /* Looper */);

//            Toast.makeText(BaseAppCompatActivity.this, "Retrieving Current Location...", Toast.LENGTH_SHORT).show();
            LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            } else {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
            }
            //			mHandler.postDelayed(periodicTask, 3000);
        }
    }
    void setUpLocationClientIfNeeded() {
        if (MyApplication.getInstance().getGoogleApiClient() == null) {

            mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                    .addApi(LocationServices.API)
                    .enableAutoManage(getActivity(), this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
            //		mGoogleApiClient = new LocationClient(getApplicationContext(),this,this);
            MyApplication.getInstance().setGoogleApiClient(mGoogleApiClient);
        }
    }
    protected void getLocationPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(getContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(getContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                String[] permissions = new String[]{
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,};
                ActivityCompat.requestPermissions(getActivity(), permissions, REQUEST_PERMISSIONS_LOCATION);
            }
        }
    }

    private void setLocationUpdate(Location location) {
        if (locationUpdateListeners != null && !locationUpdateListeners.isEmpty()) {
            for (LocationUpdateListener locationUpdateListener : locationUpdateListeners) {
                locationUpdateListener.onLocationUpdated(location);
            }
        }
    }
    public void getAddress(double lat, double lng,int type) {
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);
            String add = obj.getAddressLine(0);
            add = add + "\n" + obj.getCountryName();
            add = add + "\n" + obj.getCountryCode();
            add = add + "\n" + obj.getAdminArea();
            add = add + "\n" + obj.getPostalCode();
            add = add + "\n" + obj.getSubAdminArea();
            add = add + "\n" + obj.getLocality();
            add = add + "\n" + obj.getSubThoroughfare();
            Log.e("IGA", "Address" + add);
            lats = lat;
            longs = lng;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTOCOMPLETE_REQUEST_SOURCE && resultCode == RESULT_OK) {

            Place place;
            place = PlaceAutocomplete.getPlace(getContext(), data);
            LatLng latLng = place.getLatLng();
            latitude=latLng.latitude+"";
            longitude=latLng.longitude+"";

            String latdest=latsDes+"";
            Log.e("lasDes",latdest);
            if (!latdest.equals("0.0")){
                onPlotLocation(true,LOCATION_SOURCE_DESTINATION,latLng.latitude,latLng.longitude,AUTOCOMPLETE_REQUEST_SOURCE);
            }
            else
            {
                onPlotLocation(true,LOCATION_SOURCE,latLng.latitude,latLng.longitude,AUTOCOMPLETE_REQUEST_SOURCE);
            }

            getAddress(latLng.latitude, latLng.longitude,AUTOCOMPLETE_REQUEST_SOURCE);
        }
        else
        if (requestCode == AUTOCOMPLETE_REQUEST_DES && resultCode == RESULT_OK) {

            Place place;
            place = PlaceAutocomplete.getPlace(getContext(), data);
            LatLng latLng = place.getLatLng();
            String SourceLats=lats+"";

            if (!SourceLats.equals("0.0"))
            {
                onPlotLocation(true,LOCATION_SOURCE_DESTINATION,latLng.latitude,latLng.longitude,AUTOCOMPLETE_REQUEST_DES);
            }
            else {
                onPlotLocation(true,LOCATION_DESTINATION,latLng.latitude,latLng.longitude,AUTOCOMPLETE_REQUEST_DES);
            }
            getAddress(latLng.latitude, latLng.longitude,AUTOCOMPLETE_REQUEST_DES);


        }


    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(getActivity(), "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        return false;
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        View locationButton = ((View) myMapFragment.getView().findViewById(Integer.parseInt("1")).
                getParent()).findViewById(Integer.parseInt("2"));

        // and next place it, for exemple, on bottom right (as Google Maps app)
        RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
        // position on right bottom
        rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        rlp.setMargins(0, 0, 30, 30);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
               // buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        }
        else {
           // buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
      //  setupMapOnCameraChange();
    }

    @Override
    public void onConnected(Bundle bundle) {

       /* mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (ContextCompat.checkSelfPermission((Activity)getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }*/

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }
    private void displayLocation() {

        onPlotLocation(true, LOCATION_SOURCE, LastLocation.getLatitude(), LastLocation.getLongitude(),LOCATION_SOURCE);
    }
    public void onPlotLocation(boolean isMarkerNeeded, int type, double latitude, double longitude, int applyfor) {

        LatLng newLatLng = null;
        try {
            newLatLng = new LatLng(latitude, longitude);
            int height = 64;
            int width = 64;
            Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.source_pin);
            Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
            BitmapDescriptor smallMarkerIconSource = BitmapDescriptorFactory.fromBitmap(smallMarker);

            Bitmap c = BitmapFactory.decodeResource(getResources(), R.drawable.destination_pin);
            Bitmap smallMarkertwo = Bitmap.createScaledBitmap(c, width, height, false);
            BitmapDescriptor smallMarkerIconDestination = BitmapDescriptorFactory.fromBitmap(smallMarkertwo);
            if (isMarkerNeeded) {
                switch (type) {
                    case LOCATION_SOURCE:
                        mMap.clear();
                        mMap.addMarker(new MarkerOptions().position(newLatLng).icon(smallMarkerIconSource));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(newLatLng, 18));
                        break;
                    case LOCATION_DESTINATION:
                        mMap.clear();
                        mMap.addMarker(new MarkerOptions().position(newLatLng).icon(smallMarkerIconDestination));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(newLatLng, 18));
                        break;
                    case  LOCATION_SOURCE_DESTINATION:
                        mMap.clear();
                        LatLng des;
                        if (applyfor==1)
                        {
                             des=new LatLng(newLatLng.latitude,newLatLng.longitude);
                             newLatLng = new LatLng(latsDes, longsDes);
                        }
                       else {
                             des=new LatLng(lats,longs);
                        }
                        mMap.addMarker(new MarkerOptions().position(des).icon(smallMarkerIconSource));
                        mMap.addMarker(new MarkerOptions().position(newLatLng).icon(smallMarkerIconDestination));
                        List<LatLng> lstLatLngRoute =new ArrayList<LatLng>();
                        lstLatLngRoute.add(des);
                        lstLatLngRoute.add(newLatLng);
                        zoomRoute(mMap,lstLatLngRoute);

                        break;
                    default:
                        mMap.clear();
                        mMap.addMarker(new MarkerOptions().position(newLatLng).icon(BitmapDescriptorFactory.defaultMarker()));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(newLatLng, 18));
                        break;
                }
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();

        }
    }
    public void zoomRoute(GoogleMap googleMap, List<LatLng> lstLatLngRoute) {

        if (googleMap == null || lstLatLngRoute == null || lstLatLngRoute.isEmpty()) return;

        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();
        for (LatLng latLngPoint : lstLatLngRoute)
            boundsBuilder.include(latLngPoint);

        int routePadding = 100;
        LatLngBounds latLngBounds = boundsBuilder.build();

        try {

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, routePadding));
        String urlfirst = getUrl(lstLatLngRoute.get(0), lstLatLngRoute.get(1));
        FetchUrl FetchUrl = new FetchUrl();
        // Start downloading json data from Google Directions API
        FetchUrl.execute(urlfirst);
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }



    @Override
    public void onResume() {
        super.onResume();

        if (isRequestingLocationUpdates) {
            getCurrentLocation();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        super.onPause();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
        mFusedLocationClient.removeLocationUpdates(locationCallback);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
    public void showGpsDisabledDialog(){
       // Dialog dialog = new Dialog(this, getResources().getString(R.string.gps_disabled), getResources().getString(R.string.please_enable_gps));
        AlertDialog.Builder ab = new AlertDialog.Builder(getContext());
        ab.setMessage("In order to use this app you need the GPS to be enabled.");
        ab.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
            }
        });

        ab.show();
    }
    private String getUrl(LatLng origin, LatLng dest) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Sensor enabled
        String sensor = "sensor=false";
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor+"&key="+getString(R.string.places_api_key);
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;
        return url;
    }
    // Fetches data from url passed
    private class FetchUrl extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... url) {
            // For storing data from web service
            String data = "";
            try {
                // Fetching the data from web service
                data = downloadUrl(url[0]);
                Log.d("Background Task data", data.toString());
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            ParserTask parserTask = new ParserTask();
            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);

        }
    }
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                Log.d("ParserTask",jsonData[0].toString());
                DataParser parser = new DataParser();
                Log.d("ParserTask", parser.toString());

                // Starts parsing data
                routes = parser.parse(jObject);
                Log.d("ParserTask","Executing routes");
                Log.d("ParserTask",routes.toString());

            } catch (Exception e) {
                Log.d("ParserTask",e.toString());
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points;
            PolylineOptions lineOptions = null;
            // Traversing through all the routes
            try{
                for (int i = 0; i < result.size(); i++) {
                    points = new ArrayList<>();
                    lineOptions = new PolylineOptions();

                    // Fetching i-th route
                    List<HashMap<String, String>> path = result.get(i);

                    // Fetching all the points in i-th route
                    for (int j = 0; j < path.size(); j++) {
                        HashMap<String, String> point = path.get(j);

                        double lat = Double.parseDouble(point.get("lat"));
                        double lng = Double.parseDouble(point.get("lng"));
                        LatLng position = new LatLng(lat, lng);

                        points.add(position);
                    }

                    // Adding all the points in the route to LineOptions
                    lineOptions.addAll(points);
                    lineOptions.width(5);
                    lineOptions.color(Color.BLACK);
                    Log.d("onPostExecute", "onPostExecute lineoptions decoded");
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }

            // Drawing polyline in the Google Map for the i-th route
            if(lineOptions != null) {
                mMap.addPolyline(lineOptions);
            }
            else {
                Log.d("onPostExecute","without Polylines drawn");
            }
        }
    }
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);
            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();
            // Connecting to url
            urlConnection.connect();
            // Reading data from url
            iStream = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            data = sb.toString();
            Log.d("downloadUrl", data.toString());
            br.close();

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }




    protected boolean checkForLocationPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(getContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(getContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return hasLocationPermissions = false;
            } else {
                return hasLocationPermissions = true;
            }
        } else {
            return hasLocationPermissions = true;
        }
    }

}
