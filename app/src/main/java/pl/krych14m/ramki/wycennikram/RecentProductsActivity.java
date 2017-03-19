package pl.krych14m.ramki.wycennikram;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_recent_products)
@OptionsMenu(R.menu.menu_recent_products)
public class RecentProductsActivity extends AppCompatActivity {

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @ViewById(R.id.fab)
    FloatingActionButton fab;

    @Click(R.id.fab)
    void doFab() {
        Snackbar.make(fab, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show();
    }

    @OptionsItem(R.id.action_settings)
    void doSettings() {
        Snackbar.make(fab, "Settings", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show();
    }
}
