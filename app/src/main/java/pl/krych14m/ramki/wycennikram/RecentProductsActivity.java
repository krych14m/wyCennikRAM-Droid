package pl.krych14m.ramki.wycennikram;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import pl.krych14m.ramki.wycennikram.ramki.RamkiCalculator;

@EActivity(R.layout.activity_recent_products)
@OptionsMenu(R.menu.menu_recent_products)
public class RecentProductsActivity extends AppCompatActivity {

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @ViewById(R.id.fab)
    FloatingActionButton fab;

    @Bean
    RamkiCalculator calculator;

    @Click(R.id.fab)
    void doFab() {
        Snackbar.make(fab, (calculator != null ? "good" : "bad"), Snackbar.LENGTH_LONG)
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
