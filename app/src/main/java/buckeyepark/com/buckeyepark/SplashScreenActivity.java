package buckeyepark.com.buckeyepark;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class SplashScreenActivity extends ActionBarActivity {
    private static final int TIMER_RUNTIME = 10000;
    private boolean mbActive;
    private ProgressBar mProgressBar;
    private ImageView imageLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        imageLogo = (ImageView) findViewById(R.id.BuckeyeParkSplash);
        imageLogo.setImageResource(R.drawable.splashscreen);
        imageLogo.bringToFront();
        mProgressBar = (ProgressBar)findViewById(R.id.progressbar);

        final Thread timerThread = new Thread()
        {
            @Override
            public void run()
            {
                mbActive = true;
                try
                {
                    int waited = 0;

                    while(mbActive && (waited < TIMER_RUNTIME))
                    {
                        sleep(50);
                        if(mbActive)
                        {
                            waited += 200;
                            setProgress(waited);
                            updateProgress(waited);
                        }
                    }

                }
                catch(InterruptedException e)
                {
                    // do nothing
                }
                finally
                {
                    onContinue();
                }

            }

        };

        timerThread.start();
    }



    public void updateProgress(final int timePassed)
    {
        if(null != mProgressBar)
        {
            // Ignore rounding error here
            final int progress = mProgressBar.getMax() * timePassed / TIMER_RUNTIME;
            mProgressBar.setProgress(progress);
        }
    }


    @Override
    public void onDestroy()
    {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }




    public void onContinue()
    {
        Intent finished = new Intent(this, MainActivity.class);
        startActivity(finished);
    }


}
