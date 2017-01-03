/*
 * Copyright (C) 2008-2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.blackcj.customkeyboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.Keyboard.Key;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.inputmethod.InputMethodSubtype;
import android.graphics.drawable.Drawable;
import java.util.List;
import android.graphics.Typeface;
//import android.graphics.Rect;

public class LatinKeyboardView extends KeyboardView {

    static final int KEYCODE_OPTIONS = -100;
    // TODO: Move this into android.inputmethodservice.Keyboard
    static final int KEYCODE_LANGUAGE_SWITCH = -101;
	private Drawable mCustomImageSpace;
	private Drawable mCustomImageLogo;
	private Drawable dr;

    public LatinKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
		mCustomImageSpace = context.getResources().getDrawable(R.drawable.sym_keyboard_space);
		mCustomImageLogo = context.getResources().getDrawable(R.drawable.bt_logo);
		dr = context.getResources().getDrawable(R.drawable.bottom);
					    }

    public LatinKeyboardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected boolean onLongPress(Key key) {
        if (key.codes[0] == Keyboard.KEYCODE_CANCEL) {
            getOnKeyboardActionListener().onKey(KEYCODE_OPTIONS, null);
            return true;
        /*} else if (key.codes[0] == 113) {

            return true; */
        } else {
            //Log.d("LatinKeyboardView", "KEY: " + key.codes[0]);
            return super.onLongPress(key);
        }
    }

    void setSubtypeOnSpaceKey(final InputMethodSubtype subtype) {
        final LatinKeyboard keyboard = (LatinKeyboard)getKeyboard();
        //keyboard.setSpaceIcon(getResources().getDrawable(subtype.getIconResId()));
        invalidateAllKeys();
    }
@Override
public void onDraw(Canvas canvas) {
	super.onDraw(canvas);
	Paint paint = new Paint();
	paint.setTextAlign(Paint.Align.CENTER);
	//paint.setTypeface(Typeface.DEFAULT_BOLD);
	paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
	paint.setTextSize(22);
	paint.setColor(Color.WHITE);

	List<Key> keys = getKeyboard().getKeys();
	int[] arr ={32,9991,9995,33,63,9977,9979,9978,9975,9989,-2}; //unused atm
	for (Key key : keys) {            
		//Log.e("KEY", "Drawing key with code " + key.codes[0]);
		//if (Arrays.asList(arr).contains((int)key.codes[0])) {
		for (int ar: arr){
			if (key.codes[0] == ar) {

				dr.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
				dr.draw(canvas);
				if(key.label != null){
					canvas.drawText(key.label.toString(), key.x + (key.width/2), key.y + (key.height/2)+11, paint);
				}else{
				switch(key.codes[0]){
				case 32:
					mCustomImageSpace.setBounds(key.x+5, key.y, key.x + key.height, key.y + key.height);
					mCustomImageSpace.draw(canvas);
					break;
				case -2:
				//	canvas.drawText("|________|", key.x + (key.width/2), key.y + (key.height/2)+11, paint);

					mCustomImageLogo.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
					mCustomImageLogo.draw(canvas);
					break;
					}
				}

			}            
		}
		}
	}

}
