package edu.orangecoastcollege.cs273.dpham147.tipcalculator;

/**
 * Created by dpham147 on 9/8/2016.
 */
public class RestaurantBill {

    private double mAmount;
    private double mTipPercent;
    private double mTipAmount;
    private double mTotalAmount;

    public RestaurantBill() {
        mAmount = 0.0;
        mTipAmount = 0.0;
        mTipPercent = 0.0;
        mTotalAmount = 0.0;
    }

    public RestaurantBill(double mAmount, double mTipPercent) {
        this.mAmount = mAmount;
        this.mTipPercent = mTipPercent;
        recalculateAmount();
    }

    public double getmAmount() {
        return mAmount;
    }

    public void setmAmount(double mAmount) {
        this.mAmount = mAmount;
        recalculateAmount();
    }

    public double getmTipPercent() {
        return mTipPercent;
    }

    public void setmTipPercent(double mTipPercent) {
        this.mTipPercent = mTipPercent;
        recalculateAmount();
    }

    public double getmTipAmount() {
        return mTipAmount;
    }

    public void setmTipAmount(double mTipAmount) {
        this.mTipAmount = mTipAmount;
    }

    public double getmTotalAmount() {
        return mTotalAmount;
    }

    private double recalculateAmount() {
        mTipAmount = mAmount * mTipPercent;
        mTotalAmount = mAmount + mTipAmount;
    }
}