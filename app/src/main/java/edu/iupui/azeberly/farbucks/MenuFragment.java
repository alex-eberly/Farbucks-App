package edu.iupui.azeberly.farbucks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.iupui.azeberly.farbucks.model.FarbucksDataStore;
import edu.iupui.azeberly.farbucks.model.MenuItem;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {

    private RecyclerView mMenuRecyclerView;
    private MenuAdapter mMenuAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_menu, container, false);

        // Wire up controls on layout
        mMenuRecyclerView = (RecyclerView) v.findViewById(R.id.menu_recycler_view);
        mMenuRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return v;

    }

    private void updateUI() {

        // QUERY FOR A LIST OF ALL LOCATIONS FROM THE DATABASE
        FarbucksDataStore farbucksDataStore = FarbucksDataStore.getInstance(getActivity().getApplication());
        List<MenuItem> allMenuItems = farbucksDataStore.getMenuItems();

        // ADD LOCATIONS TO ADAPTER
        mMenuAdapter = new MenuAdapter(allMenuItems);
        mMenuRecyclerView.setAdapter(mMenuAdapter);
    }

    private class MenuHolder extends RecyclerView.ViewHolder {

        public TextView mTitleTextView;

        public MenuHolder(View itemView) {
            super(itemView);

            mTitleTextView = (TextView) itemView;
        }

    }


    private class MenuAdapter extends RecyclerView.Adapter<MenuHolder> {

        private List<MenuItem> mMenuItemList;

        public MenuAdapter(List<MenuItem> allMenuItems) {
            mMenuItemList = allMenuItems;
        }

        @Override
        public MenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            return new MenuHolder(view);
        }

        @Override
        public void onBindViewHolder(MenuHolder holder, int position) {
            MenuItem thisMenuItem = mMenuItemList.get(position);
            holder.mTitleTextView.setText(thisMenuItem.getName());
        }

        @Override
        public int getItemCount() {
            return mMenuItemList.size();
        }

    }
}

