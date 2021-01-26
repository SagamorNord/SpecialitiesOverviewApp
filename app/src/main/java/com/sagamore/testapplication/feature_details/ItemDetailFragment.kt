package com.sagamore.testapplication.feature_details

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.sagamore.testapplication.utils.convertDate
import com.sagamore.testapplication.R
import com.sagamore.testapplication.service.data.EmployeeModel
import com.sagamore.testapplication.utils.toStringWithPostfix
import com.sagamore.testapplication.utils.stringFormatting
import org.joda.time.LocalDate
import org.joda.time.Years
import org.joda.time.format.DateTimeFormat
import java.util.regex.Pattern

class ItemDetailFragment : Fragment() {

    private var item: EmployeeModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                item = it.getSerializable(ARG_ITEM_ID) as EmployeeModel?

                val iconToolbar =
                    activity?.findViewById<ImageView>(R.id.icon)

                Glide.with(iconToolbar!!)
                    .load(item?.avatr_url)
                    .into(iconToolbar)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        item?.let {
            rootView.apply {
                findViewById<TextView>(R.id.firstName).text = it.f_name.stringFormatting()
                findViewById<TextView>(R.id.lastName).text = it.l_name.stringFormatting()
                findViewById<TextView>(R.id.birthday).text = convertDate(it.birthday, "dd.MM.yyyy")
                findViewById<TextView>(R.id.age).text = calculateAge(it.birthday)
                findViewById<TextView>(R.id.speciality).text = it.specialty[0].name
            }
        }

        return rootView
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun calculateAge(birthDate: String): String {
        return if (birthDate.isNullOrEmpty()) {
            ""
        } else {
            val date =
                if (Pattern.matches(DATE_PATTERN_START_DAY, birthDate)) {
                    DATE_FORMAT_START_DAY.parseLocalDate(birthDate)
                } else {
                    DATE_FORMAT_START_YEAR.parseLocalDate(birthDate)
                }
            Years
                .yearsBetween(date, LocalDate.now())
                .years
                .toStringWithPostfix()
        }
    }

    companion object {

        const val ARG_ITEM_ID = "item_id"
        private val DATE_FORMAT_START_DAY = DateTimeFormat.forPattern("dd-MM-yyyy")
        private val DATE_FORMAT_START_YEAR = DateTimeFormat.forPattern("yyyy-MM-dd")
        private const val DATE_PATTERN_START_DAY = "([0-9]{2})-([0-9]{2})-([0-9]{4})"
    }
}
