package com.development.loginmvvm.ui
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.development.loginmvvm.ItemAdapter
import com.development.loginmvvm.R
import com.development.loginmvvm.data.network.repository.ItuneRepository
import com.development.loginmvvm.databinding.ActivityItunesTabBinding
import com.development.loginmvvm.model.itunes.Result
import com.development.loginmvvm.room.AppDB
import com.development.loginmvvm.viewmodel.itunemodel.ItuneViewModel
import com.development.loginmvvm.viewmodel.itunemodel.ItuneViewModelFactory
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.simplifiedcoding.mvvmsampleapp.data.network.MyApi


class ITuneActivity : AppCompatActivity() {

    private lateinit var mItuneViewModel: ItuneViewModel
    private lateinit var factory: ItuneViewModelFactory
    private var binding: ActivityItunesTabBinding? = null
    private val mVideoAdapter : ItemAdapter by lazy {
        // runs on first access
        ItemAdapter(null,onItemSelected)
    }

    private val mHistoryAdapter : ItemAdapter by lazy {
        // runs on first access
        ItemAdapter(null,onItemSelected)
    }

    private val recyclerView:RecyclerView by lazy {
         binding!!.recyclerView
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_itunes_tab)
        val api= MyApi.invoke()
        val mDB=AppDB.getDataseClient(applicationContext)
        factory= ItuneViewModelFactory(ItuneRepository(api = api,mDB))
        mItuneViewModel = ViewModelProvider(this, factory).get(ItuneViewModel::class.java)

        binding!!.lifecycleOwner = this;

        binding!!.ituneViewModel = mItuneViewModel

        binding!!.tabLayout.addTab(binding!!.tabLayout.newTab().setText(getString(R.string.video)));
        binding!!.tabLayout.addTab(binding!!.tabLayout.newTab().setText(getString(R.string.history)));

        // bind RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = mVideoAdapter


        mItuneViewModel.iTuneResponse.observe(this,{
            if (it.resultCount != null) {
                it.results?.let { it1 ->
                    lifecycleScope.launch{
                        mItuneViewModel.insertCollectionList(it1)
                        mVideoAdapter.setList(it1.toMutableList())
                        recyclerView.adapter = mVideoAdapter
                    }

                }

            } else {
                Toast.makeText(this@ITuneActivity, "error", Toast.LENGTH_LONG).show()
            }
        })


        mItuneViewModel.getHistoryData()?.observe(this,{
            if (it != null) {
                mHistoryAdapter.setList(it.toMutableList())
            }

        })

        mItuneViewModel.error.observe(this,{
            Toast.makeText(this@ITuneActivity, it.message, Toast.LENGTH_LONG).show()
        })




        binding!!.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                mItuneViewModel.selectedTab.value=tab?.position
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Handle tab reselect
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle tab unselect
            }
        })

        mItuneViewModel.selectedTab.observe(this,{ it->
            recyclerView.adapter=null
            when(it){
                0->{
                    fetchVideoList()
                }

                1->{
                    recyclerView.adapter=mHistoryAdapter
                }
            }
        })




    }


    private fun fetchVideoList(){
        if (mItuneViewModel.iTuneResponse.value==null){
            lifecycleScope.launch(Dispatchers.IO) {
                mItuneViewModel.getItuneService()
            }
        }
        else{
            mItuneViewModel.iTuneResponse.value?.let {
                recyclerView.adapter = mVideoAdapter
            }
        }


    }

    private val onItemSelected = {
            item: Result? ->

        when( mItuneViewModel.selectedTab.value){
            0->{
                item?.let {
                    lifecycleScope.launch(Dispatchers.IO) {
                        it.isVisited =true
                        mItuneViewModel.updateRow(it)
                    }

                    val intent = Intent(this, CollectionDetailActivity::class.java).apply {
                        putExtra(CollectionDetailActivity.KEY_RESULT_DATA, it)
                    }
                    startActivity(intent)
                }

            }

            1->{

            }
            else -> {

            }
        }
    }


}