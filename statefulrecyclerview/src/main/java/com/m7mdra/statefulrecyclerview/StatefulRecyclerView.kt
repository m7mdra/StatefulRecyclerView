package com.m7mdra.statefulrecyclerview

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.textview.MaterialTextView


class StatefulRecyclerView : FrameLayout, StatefulView {
    private var currentState = State.Default

    private fun onStateChange() {
        when (currentState) {
            State.Data -> data()
            State.Loading -> loading()
            State.Error -> error()
            State.Empty -> empty()
            State.Default -> default()
        }
    }

    internal lateinit var progressBar: CircularProgressIndicator
    internal lateinit var errorLayout: LinearLayout
    internal lateinit var errorMessageTextView: MaterialTextView
    internal lateinit var errorRetryButton: MaterialButton
    internal lateinit var emptyMessageTextView: MaterialTextView
    internal lateinit var recyclerView: RecyclerView

    constructor(context: Context) : super(context) {
        isSaveEnabled = true
        inflate(context, R.layout.stateful_recycler_view_layout, this)

    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        isSaveEnabled = true
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

    override fun showEmpty() {
        empty()
    }


    override fun showData() {
        data()
    }

    override fun showError() {
        error()
    }

    override fun showLoading() {
        loading()
    }

    override fun showDefault() {
        default()
    }

    private fun empty() {
        progressBar.gone()
        errorLayout.gone()
        emptyMessageTextView.visible()
        recyclerView.gone()
    }

    private fun data() {
        progressBar.gone()
        errorLayout.gone()
        emptyMessageTextView.gone()
        recyclerView.visible()
    }

    private fun loading() {
        progressBar.visible()
        errorLayout.gone()
        emptyMessageTextView.gone()
        recyclerView.gone()
    }

    private fun error() {
        progressBar.gone()
        errorLayout.visible()
        emptyMessageTextView.gone()
        recyclerView.gone()
    }

    private fun default() {
        progressBar.gone()
        errorLayout.gone()
        emptyMessageTextView.gone()
        recyclerView.gone()
    }

    private class SavedState : BaseSavedState {
        var state: State = State.Default

        constructor(superState: Parcelable?) : super(superState)
        private constructor(parcel: Parcel) : super(parcel) {
            state = parcel.readSerializable() as State
        }

        override fun writeToParcel(out: Parcel?, flags: Int) {
            super.writeToParcel(out, flags)
            out?.writeSerializable(state)
        }


        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<SavedState> {
            override fun createFromParcel(parcel: Parcel): SavedState {
                return SavedState(parcel)
            }

            override fun newArray(size: Int): Array<SavedState?> {
                return arrayOfNulls(size)
            }
        }
    }

}