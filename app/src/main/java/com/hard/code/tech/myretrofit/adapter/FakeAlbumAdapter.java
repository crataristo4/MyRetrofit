package com.hard.code.tech.myretrofit.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hard.code.tech.myretrofit.R;
import com.hard.code.tech.myretrofit.databinding.LayoutListAlbumBinding;
import com.hard.code.tech.myretrofit.models.Albums;

import java.util.List;

public class FakeAlbumAdapter extends RecyclerView.Adapter<FakeAlbumAdapter.AlbumAdapter> {
    List<Albums> albumsArrayList;
    Context context;

    public FakeAlbumAdapter(Context context, List<Albums> albums) {
        this.albumsArrayList = albums;
        this.context = context;
    }

    @NonNull
    @Override
    public AlbumAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        /*return new AlbumAdapter(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_list_album, viewGroup, false));*/

        LayoutListAlbumBinding layoutListAlbumBinding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext())
                        , R.layout.layout_list_album, viewGroup, false);

        return new AlbumAdapter(layoutListAlbumBinding);


    }

    @Override
    public void onBindViewHolder(@NonNull AlbumAdapter albumAdapter, int i) {
        Albums albums = albumsArrayList.get(i);
//        albumAdapter.txtTitle.setText(albums.getTitle());
//        albumAdapter.TXTiD.setText(String.valueOf(albums.getId()));
//        Glide.with(context)
//                .load(albumsArrayList.get(i).getThumbnailUrl())
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(albumAdapter.imgAlbumPhoto);

        //albumAdapter.onBind(albums);
        albumAdapter.layoutListAlbumBinding.setAlbums(albums);

    }

    @Override
    public int getItemCount() {
        return albumsArrayList == null ? 0 : albumsArrayList.size();
    }


    static class AlbumAdapter extends RecyclerView.ViewHolder {
//        TextView  txtTitle,TXTiD;
//        ImageView imgAlbumPhoto;

        LayoutListAlbumBinding layoutListAlbumBinding;
        Albums albums;

        AlbumAdapter(@NonNull LayoutListAlbumBinding itemView) {
            super(itemView.getRoot());

            layoutListAlbumBinding = itemView;

//            TXTiD = itemView.findViewById(R.id.txtAlbumId);
//            txtTitle = itemView.findViewById(R.id.txtAlbumTitle);
//            imgAlbumPhoto = itemView.findViewById(R.id.imgAlbumPhoto);


        }

        void onBind(Albums albums) {
            this.albums = albums;
            layoutListAlbumBinding.txtAlbumId.setText(String.valueOf(albums.getAlbumId()));
            layoutListAlbumBinding.txtAlbumTitle.setText(albums.getTitle());


        }
    }
}
