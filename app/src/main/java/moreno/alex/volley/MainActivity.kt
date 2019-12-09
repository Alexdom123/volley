package moreno.alex.volley

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.StringRequest

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cache = DiskBasedCache(cacheDir, 1024 * 1024)
        val network = BasicNetwork(HurlStack())

        val requestQueue = RequestQueue(cache, network).apply {
            start()
        }
        var url = "https://randomuser.me/api/?results=1"

        val stringRequest = StringRequest(Request.Method.GET,url,
            Response.Listener<String> { response ->
                Log.d("respuesta:", response.toString())
            },
            Response.ErrorListener { error ->
                Log.wtf("error volley", error.localizedMessage)
            })
        requestQueue.add(stringRequest)
    }
}
