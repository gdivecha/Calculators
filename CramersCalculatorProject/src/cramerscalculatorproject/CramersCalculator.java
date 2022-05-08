/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cramerscalculatorproject;

import java.util.ArrayList;

/**
 *
 * @author Gaurav Divecha
 */
public class CramersCalculator {
    public static double determinant(double[][] arr){
        int numOfCols = arr[0].length;
        int numOfRows = arr.length;
        double sum = 0;
        if(numOfCols>2){
            for(int i=0; i<numOfCols; i++){
                double temp = arr[0][i];
                if((i%2)==1){
                    temp *= -1;
                }
                double tempArray[][] = new double[numOfCols-1][numOfCols-1];
                int count = 0;
                for(int j=1; j<numOfRows; j++){
                    for(int k=0; k<numOfCols; k++){
                        if(i!=k){
                            tempArray[(count/(numOfRows-1))][count%(numOfCols-1)] = arr[j][k];
                            count++;
                        }
                    }
                }
                sum += temp*determinant(tempArray);
            }
            return sum;
        }
        else if(numOfCols==2){
            return (arr[0][0]*arr[1][1])-(arr[1][0]*arr[0][1]);
        }
        return arr[0][0];
    }
    public static ArrayList<Double> findColDeterminant(double[][] coefficients, double[] equates) throws Exception{
        ArrayList<Double> determinants = new ArrayList<Double>();
        ArrayList<Double> values = new ArrayList<Double>();
        ArrayList<Double> held = new ArrayList<Double>();
        double primaryDet = determinant(coefficients.clone());
        if(primaryDet==0){
            throw new Exception();
        }
        values.clear();
        for(int i=0; i<coefficients[0].length; i++){
            double[][] tempMatrix = coefficients.clone();
            held.clear();
            for(int j=0; j<equates.length; j++){
                held.add(coefficients[j][i]);
                tempMatrix[j][i] = equates[j];
            }
            determinants.add(determinant(tempMatrix));
            for(int k=0; k<equates.length; k++){
                tempMatrix[k][i] = held.get(k);
            }
        }
        for(Double doub : determinants){
            values.add(doub/primaryDet);
        }
        return values;
    }
}