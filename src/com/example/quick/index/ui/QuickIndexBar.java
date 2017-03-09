package com.example.quick.index.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class QuickIndexBar extends View {

	private Paint paint;
	private int cellWidth;
	private int measuredHeight;
	private float cellHight;
	private int index;

	private static final String[] LETTERS = new String[] { "A", "B", "C", "D",
			"E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
			"R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

	public QuickIndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		paint = new Paint();
		paint.setFlags(Paint.ANTI_ALIAS_FLAG); // 设置抗锯齿
		paint.setColor(Color.WHITE);// 设置画笔为白色
		paint.setTypeface(Typeface.DEFAULT_BOLD);// 字体加粗
		paint.setTextSize(32);
	}

	public QuickIndexBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public QuickIndexBar(Context context) {
		this(context, null);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		cellWidth = getMeasuredWidth();// 测量的宽度，也是单元格的宽度
		measuredHeight = getMeasuredHeight();
		cellHight = measuredHeight * 1.0f / LETTERS.length;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// 绘制A-Z字母
		for (int i = 0; i < LETTERS.length; i++) {
			String letter = LETTERS[i];
			// 计算每个字母的X,Y值。
			float x = cellWidth * 0.5f - paint.measureText(letter) * 0.5f;

			// 获取指定字符串指定区域的宽高信息
			Rect bounds = new Rect();
			paint.getTextBounds(letter, 0, letter.length(), bounds);
			int textHeight = bounds.height();

			// 求出y坐标值
			float y = cellHight * 0.5f + textHeight * 0.5f + i * cellHight;
			
			//设置画笔的颜色
			paint.setColor(i==currentIndex?Color.GRAY:Color.RED);
			canvas.drawText(letter, x, y, paint);
		}
	}

	private int currentIndex = -1;
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			index = (int) (event.getY() / cellHight);
			//判断当前的索引跟之前的索引是否不一样
			if(index!=currentIndex){
				if(index>=0 && index<LETTERS.length){
					String letters = LETTERS[index];
					if(onletterupdatelistener!=null){
						onletterupdatelistener.OnLetterUpdate(letters);
					}
					currentIndex = index;//更新索引
				}
			}
			break;
		case MotionEvent.ACTION_MOVE:
			index = (int) (event.getY() / cellHight);
			//判断当前的索引跟之前的索引是否不一样
			if(index!=currentIndex){
				if(index>=0 && index<LETTERS.length){
					String letters = LETTERS[index];
					if(onletterupdatelistener!=null){
						onletterupdatelistener.OnLetterUpdate(letters);
					}
					currentIndex = index;//更新索引
				}
			}
			break;
		case MotionEvent.ACTION_UP:
			currentIndex = -1;
			break;
		default:
			break;
		}
		invalidate();//重新绘制，会走ondraw()方法
		return true;
	}
	OnLetterUpdateListener onletterupdatelistener;
	
	public void SetOnLetterUpdateListener(OnLetterUpdateListener onletterupdatelistener){
		this.onletterupdatelistener = onletterupdatelistener;
	}
	
	//使用接口，创建一个回调，当点击或滑动字母时，把字母传到activity界面
	public interface OnLetterUpdateListener{
		void OnLetterUpdate(String letter);
	}

}
