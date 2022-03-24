package com.m7mdra.statefulrecyclerview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.textview.MaterialTextView
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

class StatefulRecyclerView : FrameLayout, StatefulView {
    private var currentState = State.Default


    private fun onStateChange() {
        when (currentState) {
            State.Data -> {
                progressBar.gone()
                errorLayout.gone()
                emptyMessageTextView.gone()
                recyclerView.visible()
            }
            State.Loading -> {
                progressBar.visible()
                errorLayout.gone()

                emptyMessageTextView.gone()
                recyclerView.gone()
            }
            State.Error -> {
                progressBar.gone()
                errorLayout.visible()
                emptyMessageTextView.gone()
                recyclerView.gone()
            }
            State.Empty -> {
                progressBar.gone()
                errorLayout.gone()
                emptyMessageTextView.visible()
                recyclerView.gone()

            }
            State.Default -> {
                progressBar.gone()
                errorLayout.gone()
                emptyMessageTextView.gone()
                recyclerView.gone()

            }
        }
    }

    internal lateinit var progressBar: CircularProgressIndicator
    internal lateinit var errorLayout: LinearLayout
    internal lateinit var errorMessageTextView: MaterialTextView
    internal lateinit var errorRetryButton: MaterialButton
    internal lateinit var emptyMessageTextView: MaterialTextView
    internal lateinit var recyclerView: RecyclerView

    constructor(context: Context) : super(context) {
        inflate(context, R.layout.stateful_recycler_view_layout, this)

    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        inflate(context, R.layout.stateful_recycler_view_layout, this)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        progressBar = findViewById(R.id.progressBar)
        errorLayout = findViewById(R.id.errorLayout)
        errorMessageTextView = findViewById(R.id.errorMessageTextView)
        errorRetryButton = findViewById(R.id.errorRetryButton)
        emptyMessageTextView = findViewById(R.id.emptyMessageTextView)
        recyclerView = findViewById(R.id.recyclerView)
    }


    override fun state(): State {
        return currentState
    }

    override fun moveToState(newState: State) {
        currentState = newState
        onStateChange()
    }
}