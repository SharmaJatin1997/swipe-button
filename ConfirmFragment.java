package com.example.driveusers.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import com.example.driveusers.Adapters.ViewPagerAdapter;
import com.example.driveusers.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.card.MaterialCardView;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

import in.shadowfax.proswipebutton.ProSwipeButton;

public class ConfirmFragment extends Fragment implements View.OnClickListener {

    private View view, divider3;
    private LinearLayout destination;
    private MaterialCardView bookLayout;
    private static ViewPager viewPager_add;
    private TextView heading;
    private ArrayList<Integer> imagesArray = new ArrayList<Integer>();
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES = {R.layout.item_cab, R.layout.item_cab, R.layout.item_cab, R.layout.item_cab, R.layout.item_cab};
    private Button confirm_pickup, confirm_destination;
    private BottomSheetBehavior<LinearLayout> behavior;
    private LinearLayout bottomsheeet, addressBar2;
    private RelativeLayout dis_layout;
    private ImageView back_topbar;
    private ImageView add_des;
    private ProSwipeButton order_cab;

    public ConfirmFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_confirm, container, false);

        ids();
        setup();
        click();
        return view;
    }

    private void click() {
        back_topbar.setOnClickListener(this);
        bottomsheeet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void setup() {

        heading.setText("Confirm Destination");

        imagesArray.clear();
        for (int i = 0; i < IMAGES.length; i++) {
            imagesArray.add(IMAGES[i]);
        }
        viewPager_add = (ViewPager) view.findViewById(R.id.viewPager_add);
        viewPager_add.setAdapter(new ViewPagerAdapter(imagesArray, getLayoutInflater(), getActivity()));

        CirclePageIndicator indicator = (CirclePageIndicator) view.findViewById(R.id.indicator);
        indicator.setViewPager(viewPager_add);
        final float density = getResources().getDisplayMetrics().density;
        indicator.setRadius(3 * density);
        NUM_PAGES = IMAGES.length;
        final Handler handler = new Handler();
        final Runnable runnable1 = new Runnable() {
            public void run() {
                if (viewPager_add.getCurrentItem() == ConfirmFragment.this.imagesArray.size() - 1) {
                    viewPager_add.setCurrentItem(0);
                } else {
                    viewPager_add.setCurrentItem(viewPager_add.getCurrentItem() + 1);
                }
            }
        };

        confirm_destination.setOnClickListener(this);
        add_des.setOnClickListener(this);
        confirm_pickup.setOnClickListener(this);
        order_cab.setOnSwipeListener(new ProSwipeButton.OnSwipeListener() {
            @Override
            public void onSwipeConfirm() {
                // user has swiped the btn. Perform your async operation now
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        order_cab.showResultIcon(true);
                        Navigation.findNavController(view).navigate(R.id.action_confirmFragment_to_findRouting);
                    }
                }, 2000);
            }
        });
    }

    private void ids() {
        confirm_destination = view.findViewById(R.id.confirm_destination);
        confirm_pickup = view.findViewById(R.id.confirm_pickup);
        destination = view.findViewById(R.id.destination);
        bottomsheeet = view.findViewById(R.id.bottomsheet);
        behavior = BottomSheetBehavior.from(bottomsheeet);
        bookLayout = view.findViewById(R.id.bookLayout);
        order_cab = view.findViewById(R.id.order_cab);
        add_des = view.findViewById(R.id.add_des);
        dis_layout = view.findViewById(R.id.dis_layout);
        addressBar2 = view.findViewById(R.id.addressBar2);
        divider3 = view.findViewById(R.id.divider3);
        viewPager_add = view.findViewById(R.id.viewPager_add);
        heading = view.findViewById(R.id.heading);
        back_topbar = view.findViewById(R.id.back_topbar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm_destination:
                confirm_destination.setVisibility(View.GONE);
                confirm_pickup.setVisibility(View.VISIBLE);
                break;
            case R.id.confirm_pickup:
                destination.setVisibility(View.GONE);
                bookLayout.setVisibility(View.VISIBLE);
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;
            case R.id.back_topbar:
                getActivity().onBackPressed();
                break;
            case R.id.add_des:
                add_des.setVisibility(View.GONE);
                addressBar2.setVisibility(View.VISIBLE);
                divider3.setVisibility(View.VISIBLE);
                dis_layout.setVisibility(View.VISIBLE);
                break;
        }
    }
}