/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package m_okumura.hata;

import java.util.Scanner;

/**
 *
 * @author winth
 */
public class M_OkumuraHata {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la frecuencia de la senal en MHz:");
        double frecuenciaMHz = scanner.nextDouble();
        while (frecuenciaMHz < 150 || frecuenciaMHz > 1500) {
            System.out.println("Ingrese una frecuencia permitida para la senal en MHz:");
            System.out.println("de 150 a 1500 MHz");

            frecuenciaMHz = scanner.nextDouble();
        }
/////////////////////////////////////////////////////////////////////////////////////////////////        

        System.out.println("Ingrese la altura de la antena de transmision en metros:");
        double alturaAntenaTx = scanner.nextDouble();
        while (alturaAntenaTx < 30 || alturaAntenaTx > 300) {
            System.out.println("Ingrese una altura en metros permitida:");
            System.out.println("de 30 a 300 m");

            alturaAntenaTx = scanner.nextDouble();
        }
/////////////////////////////////////////////////////////////////////////////////////////////////   

        System.out.println("Ingrese la altura de la antena de recepcion en metros:");
        double alturaAntenaRx = scanner.nextDouble();
        while (alturaAntenaRx < 1 || alturaAntenaRx > 10) {
            System.out.println("Ingrese una altura en metros permitida:");
            System.out.println("de 1 a 10 m");

            alturaAntenaRx = scanner.nextDouble();
        }
/////////////////////////////////////////////////////////////////////////////////////////////////   

        System.out.println("Ingrese la distancia entre antenas en kilometros:");
        double distanciaKm = scanner.nextDouble();
        while (distanciaKm < 1 || distanciaKm > 20) {
            System.out.println("Ingrese una distancia en kilometros permitida:");
            System.out.println("de 1 a 20 Km");
            distanciaKm = scanner.nextDouble();
        }
/////////////////////////////////////////////////////////////////////////////////////////////////   

        Scanner TC = new Scanner(System.in);

        System.out.println(" ");
        System.out.println("Escoja el tipo de ciudad:");
        System.out.println("1 para ciudad pequena o mediana");
        System.out.println("2 para ciudad grande");
        /////////////////////////////////////////////////////////////////////////////////////////////////        
        double TipoCiudad = TC.nextInt();
        while (frecuenciaMHz < 1 && frecuenciaMHz > 2) {

            System.out.println(" ");
            System.out.println("Ingrese un tipo de ciudad valido:");
            System.out.println("1 para ciudad pequena o mediana");
            System.out.println("2 para ciudad grande");

            TipoCiudad = TC.nextInt();
        }
///////////////////////////////////////////////////////////////////////////////////////////////// 
        if (TipoCiudad == 2) {
            if (frecuenciaMHz < 300) {
                double d1 = (8.29 * Math.pow(Math.log10(1.54 * alturaAntenaRx), 2)) - 1.1;
                double ah = Math.round(d1 * 100.0) / 100.0;
                double d2 = 69.55 + (26.16 * Math.log10(frecuenciaMHz)) - (13.82 * Math.log10(alturaAntenaRx)) - ah + ((44.9 - 6.55 * Math.log10(alturaAntenaTx)) * Math.log10(distanciaKm));
                double loss = Math.round(d2 * 100.0) / 100.0;
                System.out.println("");
                System.out.println("A(hr): " + ah + " dB");
                System.out.println("Perdida de propagacion segun el modelo de Okumura-Hata: " + loss + " dB");

            } else {

                double d1 = (3.2 * Math.pow(Math.log10(11.75 * alturaAntenaRx), 2)) - 4.97;
                double ah = Math.round(d1 * 100.0) / 100.0;
                double d2 = 69.55 + (26.16 * Math.log10(frecuenciaMHz)) - (13.82 * Math.log10(alturaAntenaRx)) - ah + ((44.9 - 6.55 * Math.log10(alturaAntenaTx)) * Math.log10(distanciaKm));
                double loss = Math.round(d2 * 100.0) / 100.0;

                System.out.println("");
                System.out.println("A(hr): " + ah + " dB");
                System.out.println("Perdida de propagacion segun el modelo de Okumura-Hata: " + loss + " dB");

            }

        } else {

            double d1 = ((1.1 * Math.log10(frecuenciaMHz) - 0.7) * alturaAntenaRx) - ((1.56 * Math.log10(frecuenciaMHz) - 0.8));
            double ah = Math.round(d1 * 100.0) / 100.0;

            // Pérdida de propagación según el modelo de Okumura-Hata
            double d2 = 69.55 + 26.16 * Math.log10(frecuenciaMHz) - 13.82 * Math.log10(alturaAntenaRx) - ah + (44.9 - 6.55 * Math.log10(alturaAntenaTx)) * Math.log10(distanciaKm);
            double loss = Math.round(d2 * 100.0) / 100.0;

///////////////////////////////////////////////////////////////////////////////////////////////// 
            double d3 = loss - 2 * Math.pow(Math.log10(frecuenciaMHz / 28), 2) - 5.4;
            double PerdidaUB = Math.round(d3 * 100.0) / 100.0;

            double d4 = loss - 4.78 * Math.pow(Math.log10(frecuenciaMHz), 2) - 18.733 * Math.log10(frecuenciaMHz) - 40.98;

            double PerdidaOp = Math.round(d4 * 100.0) / 100.0;

            System.out.println("");
            System.out.println("A(hr): " + ah + " dB");
            System.out.println("Perdida de propagacion segun el modelo de Okumura-Hata: " + loss + " dB");
            System.out.println("La perdida de trayectoria en area suburbana es de: " + PerdidaUB + " dB");
            System.out.println("La perdida de camino en areas abiertas o rurales es de: " + PerdidaOp + " dB");

        }
/////////////////////////////////////////////////////////////////////////////////////////////////   
        System.out.println(" ");
        System.out.println("Fc: " + frecuenciaMHz + " MHz");
        System.out.println("Ht: " + alturaAntenaTx + " m");
        System.out.println("Hr: " + alturaAntenaRx + " m");
        System.out.println("d: " + distanciaKm + " Km");

    }
}
