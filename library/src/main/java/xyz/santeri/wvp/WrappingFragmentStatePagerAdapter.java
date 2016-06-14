package xyz.santeri.wvp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

/**
 * FragmentStatePagerAdapter that automatically works with WrappingViewPager, if you don't want to
 * implement the necessary logic in the adapter yourself.
 * <p>
 * Make sure you only use this adapter with a {@link WrappingViewPager}, otherwise your app
 * will crash!
 *
 * @author Santeri Elo
 * @since 14-06-2016
 */
public abstract class WrappingFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    private int mCurrentPosition = -1;

    public WrappingFragmentStatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * @param container View container (instanceof {@link WrappingViewPager}))
     * @param position  Item position
     * @param object    {@link Fragment}
     */
    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);

        if (!(container instanceof WrappingViewPager)) {
            throw new UnsupportedOperationException("ViewPager is not a WrappingViewPager");
        }

        if (position != mCurrentPosition) {
            Fragment fragment = (Fragment) object;
            WrappingViewPager pager = (WrappingViewPager) container;
            if (fragment != null && fragment.getView() != null) {
                mCurrentPosition = position;
                pager.onPageChanged(fragment.getView());
            }
        }
    }
}
