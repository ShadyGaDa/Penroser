package org.jf.Penroser;

import android.app.Activity;
import android.os.Bundle;

public class Penroser extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        PenroserGLView penroserGLView = new PenroserGLView(this);
        setContentView(penroserGLView);
    }
}
