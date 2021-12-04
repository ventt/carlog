package hu.bme.aut.carlog.fragments.details

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class DetailsPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    companion object {
        private const val NUM_PAGES: Int = 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FuelingDetailsFragment()
            1 -> ServiceDetailsFragment()
            else -> FuelingDetailsFragment()
        }
    }

    override fun getItemCount(): Int = NUM_PAGES
}
