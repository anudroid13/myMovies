// Generated code from Butter Knife. Do not modify!
package myapp.nigam.com.mymoviesapp;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DetailsActivity_ViewBinding implements Unbinder {
  private DetailsActivity target;

  @UiThread
  public DetailsActivity_ViewBinding(DetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DetailsActivity_ViewBinding(DetailsActivity target, View source) {
    this.target = target;

    target.txtSynopsis = Utils.findRequiredViewAsType(source, R.id.txt_synopsis, "field 'txtSynopsis'", TextView.class);
    target.imgBanner = Utils.findRequiredViewAsType(source, R.id.img_banner, "field 'imgBanner'", ImageView.class);
    target.txtYearOfRelease = Utils.findRequiredViewAsType(source, R.id.txt_release_year, "field 'txtYearOfRelease'", TextView.class);
    target.txtRating = Utils.findRequiredViewAsType(source, R.id.txt_rating, "field 'txtRating'", TextView.class);
    target.txtTitle = Utils.findRequiredViewAsType(source, R.id.txt_title, "field 'txtTitle'", TextView.class);
    target.recyclerViewTrailers = Utils.findRequiredViewAsType(source, R.id.recycler_view_trailers, "field 'recyclerViewTrailers'", RecyclerView.class);
    target.recyclerViewReviews = Utils.findRequiredViewAsType(source, R.id.recycler_view_reviews, "field 'recyclerViewReviews'", RecyclerView.class);
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
    target.recyclerViewTrailers = null;
    target.recyclerViewReviews = null;
  }
}
