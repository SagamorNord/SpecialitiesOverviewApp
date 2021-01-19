package com.sagamore.testapplication.feature_main_list

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.sagamore.testapplication.R
import com.sagamore.testapplication.service.data.EmployeeModel


/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ItemDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ItemListActivity : AppCompatActivity(), ItemListView {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    private val presenter: ItemListPresenter = ItemListPresenter(this)

    private lateinit var recyclerView: RecyclerView

    private var adapter = SimpleItemRecyclerViewAdapter(this, twoPane)

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        recyclerView = findViewById<View>(R.id.item_list) as RecyclerView
        recyclerView.adapter = adapter

        presenter.loadEmployees()

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        if (findViewById<NestedScrollView>(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }
    }

    companion object {
        const val BASE_URL = "https://gitlab.65apps.com/"
    }

    override fun onDataLoaded(list: List<EmployeeModel>) {
        adapter.setData(list)
    }

    override fun onNotFound() {
        Toast.makeText(
            this, "NO RESULTS FOUND",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onError(t: Throwable) {
        Toast.makeText(
            this, "ERROR IN FETCHING API RESPONSE. Try again",
            Toast.LENGTH_LONG
        ).show()
    }
}
