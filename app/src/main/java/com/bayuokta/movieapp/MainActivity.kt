package com.bayuokta.movieapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bayuokta.movieapp.about.AboutActivity
import com.bayuokta.movieapp.databinding.ActivityMainBinding
import com.bayuokta.movieapp.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var broadcastReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, HomeFragment())
                .commit()
            supportActionBar?.title = getString(R.string.app_name)
        }
    }

    private fun registerBroadCastReceiver(){
        broadcastReceiver = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                when (intent?.action) {
                    Intent.ACTION_POWER_CONNECTED -> {
                        Toast.makeText(this@MainActivity, R.string.power_connected,
                            Toast.LENGTH_SHORT).show()
                    }
                    Intent.ACTION_POWER_DISCONNECTED -> {
                        Toast.makeText(this@MainActivity, R.string.power_disconnected,
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }

        val intentFilter = IntentFilter()
        intentFilter.apply {
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
        }
        registerReceiver(broadcastReceiver, intentFilter)
    }

    override fun onStart() {
        super.onStart()
        registerBroadCastReceiver()
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return  true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       var fragment: Fragment? = null
        var title = getString(R.string.app_name)
        when(item.itemId){
            R.id.home -> {
                fragment = HomeFragment()
                title = getString(R.string.app_name)
            }
            R.id.favorite -> {
                fragment = instantiateFragment("com.bayuokta.favorite.FavoriteFragment")
                title = getString(R.string.menu_favorite)
            }
            R.id.about -> {
                startActivity(Intent(this,AboutActivity::class.java))
            }
        }

        if (fragment != null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment,fragment)
                .commit()

        }
        supportActionBar?.title = title
        return true
    }

    @Suppress("SameParameterValue")
    private fun instantiateFragment(className: String) : Fragment? {
        return try {
            Class.forName(className).newInstance() as Fragment
        } catch (e: Exception) {
            null
        }
    }

}

