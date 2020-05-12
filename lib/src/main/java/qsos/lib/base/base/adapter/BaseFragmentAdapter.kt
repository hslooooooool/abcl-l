package qsos.lib.base.base.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * @author : 华清松
 * BaseFragmentAdapter
 */
class BaseFragmentAdapter(
        fm: FragmentManager,
        private var list: List<Fragment>,
        resume: Boolean = true
) : FragmentPagerAdapter(
        fm,
        if (resume) BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT else BEHAVIOR_SET_USER_VISIBLE_HINT
) {

    override fun getItem(position: Int): Fragment {
        return list[position]
    }

    override fun getCount(): Int {
        return list.size
    }

}
