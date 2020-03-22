package com.example.studentportal

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

const val PORTAL_REQUEST_CODE = 100

class MainActivity : AppCompatActivity() {

    private val portals = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(portals) { portal : Portal -> portalItemClicked(portal) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        fab.setOnClickListener { startAddPortal() }
        initViews()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initViews() {
        rvPortal.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        rvPortal.adapter = portalAdapter
    }

    private fun startAddPortal()    {
        val intent = Intent(this, CreatePortal::class.java)
        startActivityForResult(intent, PORTAL_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK)   {
            when (requestCode)  {
                PORTAL_REQUEST_CODE ->  {
                    val portal = data!!.getParcelableExtra<Portal>(EXTRA_PORTAL)
                    portals.add(portal)
                    portalAdapter.notifyDataSetChanged()
                }
            }
        }

    }

    private fun portalItemClicked(portal : Portal) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        builder.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary))
        customTabsIntent.launchUrl(this, Uri.parse(portal.uri))
    }

    private fun createTestData() : List<Portal> {
        val portalList = ArrayList<Portal>()
        portalList.add(Portal("dlo", "https://www.dlo.hva.nl"))
        return portalList

    }
}
