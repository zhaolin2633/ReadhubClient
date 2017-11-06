package name.dmx.readhubclient.activity

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.tencent.bugly.beta.Beta
import kotlinx.android.synthetic.main.activity_main.*
import name.dmx.readhubclient.R
import name.dmx.readhubclient.adapter.TabFragmentAdapter
import name.dmx.readhubclient.fragment.NewsFragment
import name.dmx.readhubclient.fragment.TechNewsFragment
import name.dmx.readhubclient.fragment.TopicFragment
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.app.Activity



class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        toolbar.setTitle(R.string.app_name)
        val actionBarToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(actionBarToggle)
        navigationView.setNavigationItemSelectedListener(this)
        actionBarToggle.syncState()
        init()
    }

    private fun init() {
        val titleList = arrayListOf("热门话题", "科技动态", "开发者资讯")
        val fragmentList = arrayListOf(TopicFragment.newInstance(), NewsFragment.newInstance(), TechNewsFragment.newInstance())
        val tabFragmentAdapter = TabFragmentAdapter(supportFragmentManager, fragmentList, titleList)
        viewPager.adapter = tabFragmentAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_item_update -> {
                Beta.checkUpgrade()
            }
            else -> Snackbar.make(toolbar, item!!.title, Snackbar.LENGTH_SHORT).show()
        }
        drawerLayout.closeDrawers()
        return true
    }

}