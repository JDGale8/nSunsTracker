package dallasapps.nsunstracker.util;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.CardView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import dallasapps.nsunstracker.R;

import static dallasapps.nsunstracker.util.Converter.convertDpToPixels;

/**
 * Created by katy on 7/9/17.
 */

public class AssistanceAdder {

    
    public CardView createAssistanceLayout(Context context) {
        int n = 3;

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                1.0f
        );
        LinearLayout.LayoutParams paramPoint9 = new LinearLayout.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                0.9f
        );
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                1.0f
        );
        LinearLayout.LayoutParams removeBtnParams = new LinearLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT,
                1.0f
        );


        CardView assistanceCardView = new CardView(context);
        cardParams.setMargins(0, 0, 0, convertDpToPixels(16, context));
        assistanceCardView.setLayoutParams(cardParams);

        LinearLayout horizontalCardLayout = new LinearLayout(context);
        horizontalCardLayout.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout labelVerticalLayout = new LinearLayout(context);
        labelVerticalLayout.setOrientation(LinearLayout.VERTICAL);
        labelVerticalLayout.setLayoutParams(paramPoint9);

        TextView exerciseNameLabel = new TextView(context);
        exerciseNameLabel.setText("Exercise name");
        exerciseNameLabel.setWidth(convertDpToPixels(80, context));
        exerciseNameLabel.setGravity(0);
        exerciseNameLabel.setPadding(convertDpToPixels(12, context), convertDpToPixels(12, context), 0, 0);

        labelVerticalLayout.addView(exerciseNameLabel);
        horizontalCardLayout.addView(labelVerticalLayout);

        for (int i = 0; i < n + 1; i++) {
            LinearLayout verticleButtonLayout = new LinearLayout(context);
            verticleButtonLayout.setOrientation(LinearLayout.VERTICAL);
            verticleButtonLayout.setLayoutParams(param);
            Button setButton = new Button(context);
            setButton.setText("--\nx8");
            verticleButtonLayout.addView(setButton);
            horizontalCardLayout.addView(verticleButtonLayout);
        }

        Button removeButton = new Button(context);
        removeButton.setText("remove");
        removeButton.setTextSize(8);
        removeButton.setLayoutParams(removeBtnParams);

        LinearLayout verticleButtonLayout = new LinearLayout(context);
        verticleButtonLayout.setOrientation(LinearLayout.VERTICAL);
        verticleButtonLayout.setLayoutParams(param);
        verticleButtonLayout.addView(removeButton);
        horizontalCardLayout.addView(verticleButtonLayout);

        assistanceCardView.addView(horizontalCardLayout);

        return assistanceCardView;
    }


}
