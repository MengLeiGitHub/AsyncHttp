package com.async.apkupdate.defaults;

import android.content.Context;

import com.async.apkupdate.R;
import com.async.apkupdate.dialog.ApkUpdateDialog;

/**
 * Created by admin on 2016/10/20.
 */
public class DefaultDialog extends ApkUpdateDialog {
    public DefaultDialog(Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.app_update_custom_dialog;
    }

    @Override
    public int getCancleViewId() {
        return R.id.cancel;
    }

    @Override
    public int getWholeViewId() {
        return R.id.confirm;
    }

    @Override
    public int getTitleViewId() {
        return R.id.dialog_title;
    }

    @Override
    public int getContentViewId() {
        return R.id.dialog_deatail;
    }

    @Override
    public int customeDialogWidth(int fullWidth) {
        return (int) (fullWidth*0.5);
    }

    @Override
    public int getIncrementId() {
        return 0;
    }
}
