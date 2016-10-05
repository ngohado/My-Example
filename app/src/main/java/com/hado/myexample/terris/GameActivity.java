package com.hado.myexample.terris;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.Toast;

import com.hado.myexample.R;
import com.hado.myexample.activity.BaseActivity;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.Bind;
import butterknife.OnClick;

public class GameActivity extends BaseActivity {

    @Bind(R.id.edt_column)
    EditText edtColumn;

    @Bind(R.id.edt_row)
    EditText edtRow;

    @Bind(R.id.board)
    LinearLayout layoutBoard;

    private int mColumn;
    private int mRow;
    private SquareButton mZeroButton;

    private Bitmap[] arrayBitmaps;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int setContentViewID() {
        return R.layout.activity_game;
    }

    @OnClick(R.id.btn_create)
    public void createOnClick() {
        try {
            mColumn = Integer.parseInt(edtColumn.getText().toString());
            mRow = Integer.parseInt(edtRow.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Wrong format numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        cutImage();
        generateBoard();
    }

    private void cutImage() {
        arrayBitmaps = new Bitmap[mColumn * mRow];
        Bitmap rootBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        int size = Math.min(rootBitmap.getWidth(), rootBitmap.getHeight()) / mColumn;
        int current = 0;
        for (int i = 0; i < mRow; i++) {
            for (int j = 0; j < mColumn; j++) {
                arrayBitmaps[current++] = Bitmap.createBitmap(rootBitmap, j * size, i * size, size, size);
            }
        }

        arrayBitmaps[0] = BitmapFactory.decodeResource(getResources(), R.drawable.root_background);

    }

    private void generateBoard() {
        ArrayList<Integer> shuffledNumbers = shuffleNumbers(mRow, mColumn);
        int index = 0;
        for (int i = 0; i < mRow; i++) {
            LinearLayout layoutRow = new LinearLayout(this);
            layoutRow.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            layoutRow.setOrientation(LinearLayout.HORIZONTAL);

            for (int j = 0; j < mColumn; j++) {
                SquareButton node = new SquareButton(this);
                node.setScaleType(ImageView.ScaleType.FIT_XY);
                node.setPadding(1, 1, 1, 1);
                node.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
                if (shuffledNumbers.get(index).intValue() == 0) {
                    node.setImageBitmap(arrayBitmaps[shuffledNumbers.get(index++)]);
                    mZeroButton = node;
                } else {
                    node.setImageBitmap(arrayBitmaps[shuffledNumbers.get(index++)]);
                }

                node.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SquareButton node = (SquareButton) v;
                        if (node.point.columnIndex == mZeroButton.point.columnIndex && node.point.rowIndex == mZeroButton.point.rowIndex)
                            return;

                        swapToZeroButton(node);
                    }
                });

                node.point = new Point(i, j);
                layoutRow.addView(node);
            }

            layoutBoard.addView(layoutRow);
        }
    }

    private void swapToZeroButton(SquareButton node) {
        if ((node.point.columnIndex == mZeroButton.point.columnIndex && Math.abs(node.point.rowIndex - mZeroButton.point.rowIndex) == 1)
                || (node.point.rowIndex == mZeroButton.point.rowIndex && Math.abs(node.point.columnIndex - mZeroButton.point.columnIndex) == 1)) {
            Drawable rootDrawable = mZeroButton.getDrawable();
            mZeroButton.setImageDrawable(node.getDrawable());
            node.setImageDrawable(rootDrawable);
            mZeroButton = node;
        }
    }

    private ArrayList<Integer> shuffleNumbers(int row, int column) {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < row * column; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers;
    }
}
