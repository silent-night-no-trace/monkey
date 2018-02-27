package com.google.style.model.first;

/**
 * @author liangz
 * @date 2018/2/22 18:06
 **/
public final class Complex {
    private final  Double re;
    private final  Double im;

    public Complex(Double re,Double im){
        this.re = re;
        this.im = im;
    }

    public Double realPart(){
        return re;
    }
    public Double imaginaryPart(){
        return im;
    }

    public Complex add(Complex c){
        return new Complex(re+c.re,im+c.im);
    }

    public Complex subtract(Complex c){
        return  new Complex(re-c.re,im-c.im);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return  true;
        }

        if(!(obj instanceof Complex)){
            return  false;
        }
        Complex c = (Complex)obj;

        return Double.compare(re,c.re) == 0 && Double.compare(im,c.im) == 0;
    }

    @Override
    public int hashCode() {
        int result = 17 + hashDouble(re);
        result = 31 * result +hashDouble(im);
        return result;
    }

    private int hashDouble(Double val){
        long longBits = Double.doubleToLongBits(re);
        return (int)(longBits ^ (longBits >>> 32));
    }

    @Override
    public String toString() {
        return "("+re+"+"+im+"+)";
    }
}
