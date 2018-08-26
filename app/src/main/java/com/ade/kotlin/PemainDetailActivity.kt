package com.ade.kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import com.ade.kotlin.api.API
import com.ade.kotlin.model.PlayerDetail
import com.ade.kotlin.presenter.PemainDetailPresenter
import com.ade.kotlin.util.invisible
import com.ade.kotlin.util.visible
import com.ade.kotlin.view.PemainDetailInterface
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_player_detail.*

class PemainDetailActivity : AppCompatActivity(), PemainDetailInterface {

    private lateinit var progressBar: ProgressBar
    private lateinit var pemainDetailPresenter: PemainDetailPresenter
    private lateinit var idPlayer: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        supportActionBar?.title = "Player Detail"

        progressBar = findViewById(R.id.progress_bar_player)

        val myIntent = intent

        idPlayer = myIntent.getStringExtra("id")
        val request = API()
        val gson = Gson()

        pemainDetailPresenter = PemainDetailPresenter(this, request, gson)
        pemainDetailPresenter.getPlayerList(idPlayer)

    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showPlayerDetail(data: List<PlayerDetail>) {
        Picasso.get().load(data.get(0).strFanart1).into(iv_player_detail)
        txt_weight.text = data.get(0).strWeight
        txt_height.text = data.get(0).strHeight
        txt_position_player.text = data.get(0).strPosition
        txt_forward_player_detail.text = data.get(0).strDescriptionEN
    }
}
