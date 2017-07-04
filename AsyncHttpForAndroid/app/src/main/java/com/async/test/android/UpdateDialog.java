package com.async.test.android;

import android.content.Context;

import com.async.apkupdate.dialog.ApkUpdateDialog;


/**
 * Created by admin on 2016/10/20.
 */
public class UpdateDialog extends ApkUpdateDialog {
    public UpdateDialog(Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.update_custom_dialog;
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
        return (int) (fullWidth*0.9);
    }

    @Override
    public int getIncrementId() {
        return R.id.incrementUpdate;
    }
}
