package com.example.kaash.barcode;

import com.google.android.gms.vision.barcode.Barcode;

public interface OnNewBarcodeListener {
    void onNewItem(Barcode item);
}
