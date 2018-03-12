// Generated code from Butter Knife. Do not modify!
package myapp.nigam.com.mymoviesapp;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DetailsActivity_ViewBinding implements Unbinder {
  private DetailsActivity target;

  private View view2131230803;

  @UiThread
  public DetailsActivity_ViewBinding(DetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DetailsActivity_ViewBinding(final DetailsActivity target, View source) {
    this.target = target;

    View view;
    target.txtSynopsis = Utils.findRequiredViewAsType(source, R.id.txt_synopsis, "field 'txtSynopsis'", TextView.class);
    target.imgBanner = Utils.findRequiredViewAsType(source, R.id.img_banner, "field 'imgBanner'", ImageView.class);
    target.txtYearOfRelease = Utils.findRequiredViewAsType(source, R.id.txt_release_year, "field 'txtYearOfRelease'", TextView.class);
    target.txtRating = Utils.findRequiredViewAsType(source, R.id.txt_rating, "field 'txtRating'", TextView.class);
    target.txtTitle = Utils.findRequiredViewAsType(source, R.id.txt_title, "field 'txtTitle'", TextView.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.img_fav, "field 'imgFav' and method 'addfav'");
    target.imgFav = Utils.castView(view, R.id.img_fav, "field 'imgFav'", ImageView.class);
    view2131230803 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addfav();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    DetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.txtSynopsis = null;
    target.imgBanner = null;
    target.txtYearOfRelease = null;
    target.txtRating = null;
    target.txtTitle = null;
    target.recyclerView = null;
    target.imgFav = null;

    view2131230803.setOnClickListener(null);
    view2131230803 = null;
  }
}
