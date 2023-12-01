package MainFrameLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import TabLayout.CO2Fragment;
import TabLayout.HumidityFragment;
import TabLayout.PMFragment;
import TabLayout.TempFragment;

public class VpAdapter extends FragmentStateAdapter {


    public VpAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new TempFragment();
            case 1:
                return new HumidityFragment();
            case 2:
                return new CO2Fragment();
            case 3:
                return new PMFragment();
            default:
                return new TempFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}