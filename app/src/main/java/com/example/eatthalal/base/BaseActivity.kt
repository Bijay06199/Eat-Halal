package com.example.eatthalal.base


import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.eatthalal.BR
import com.example.eatthalal.utils.setupUI
import io.paperdb.Paper
import org.koin.android.ext.android.inject

abstract class BaseActivity<DATA_BINDING : ViewDataBinding, VIEW_MODEL : BaseViewModel> : AppCompatActivity() {

    lateinit var viewDataBinding: DATA_BINDING
    private var baseViewModel: VIEW_MODEL? = null
    val preferenceManager: com.example.eatthalal.data.prefs.PreferenceManager by inject()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Paper.init(this)
        performDataBinding()
       setupUI(viewDataBinding.root)

    }

    private fun performDataBinding() {
        viewDataBinding= DataBindingUtil.setContentView(this,getLayoutId())
        this.baseViewModel=baseViewModel?:getViewModel()
        viewDataBinding.apply {
            setVariable(getBindingVariable(),baseViewModel)
            setLifecycleOwner(this@BaseActivity)
            executePendingBindings()
        }

    }

    /** @return layout resource id*/

    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract fun getViewModel(): VIEW_MODEL

    /**
     * Override for set binding variable
     *
     * @return variable id
     */

    open fun getBindingVariable(): Int= BR.viewModel

}
