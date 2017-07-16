package moe.christina.decision.screen.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import moe.christina.decision.R
import org.jetbrains.anko.find

class LayerEditorActivity : BaseDecisionActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(find<Toolbar>(R.id.toolbar))
    }
}