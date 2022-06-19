package models;

/**
	 * Clase encargada de crear un perceptron que aprenda a diferenciar hombres de mujeres con base en ciertas caracteristicas
	 *
	 */
	public class Perceptron {

		public static double APRENDIZAJE = 0.25;
		private int[][] dataSet = { { 1, 0, 1, 0, 0, 1, 1 }, 
									{ 0, 0, 1, 0, 0, 1, 1 }, 
									{ 0, 1, 1, 1, 1, 0, 0 },
									{ 1, 1, 1, 1, 1, 1, 1 }, 
									{ 0, 0, 0, 0, 0, 0, 0 }, 
									{ 0, 1, 0, 1, 0, 0, 0 }, 
									{ 0, 1, 0, 1, 0, 1, 0 },
									{ 1, 0, 1, 0, 1, 0, 1 }, 
									{ 1, 0, 1, 0, 1, 1, 1 }, 
									{ 1, 1, 0, 0, 1, 1, 1 }, 
									{ 1, 1, 0, 0, 1, 0, 0 },
									{ 1, 1, 0, 0, 0, 1, 0 }, 
									{ 1, 1, 1, 0, 0, 0, 1 }, 
									{ 1, 1, 1, 0, 1, 0, 1 }, 
									{ 1, 1, 1, 1, 0, 0, 1 } 
									};
		private int[][] dataSetTest = { { 1, 1, 1, 0, 1, 0, 1 }, 
										{ 0, 0, 0, 0, 0, 0, 0 }, 
										{ 1, 1, 1, 1, 1, 1, 1 },
										{ 0, 1, 0, 1, 0, 1, 0 }, 
										{ 1, 0, 1, 0, 1, 0, 1 },
										{ 1, 1, 1, 0, 0, 0, 1 }
										};
		public double pesoX1, pesoX2, pesoX3, pesoX4, pesoX5, pesoX6, baias;

		/**
		 * Metodo constructor de la clase, inicializa las variables de los pesos y el
		 * baias
		 */
		public Perceptron() {
			this.pesoX1 = 0;
			this.pesoX2 = 0;
			this.pesoX3 = 0;
			this.pesoX4 = 0;
			this.pesoX5 = 0;
			this.pesoX6 = 0;
			this.baias = 0;
		}

		/**
		 * Metodo para crear la funcion de activacion hardLimit
		 * 
		 * @param value valor a convertir para activar
		 * @return el valor de la activacion
		 */
		private int hardLimit(double value) {
			if (value >= 0) {
				return 1;
			} else {
				return 0;
			}
		}

		/**
		 * Metodo en donde se inicia el aprendizaje del peceptron
		 * 
		 * @param iteratios la cantidad de iteraciones que se desea realizae
		 */
		public void aprendizaje(int iteratios) {
			for (int i = 0; i < iteratios; i++) {
				evaluarPesos();
			}
			
		}

		/**
		 * Evalua la funcion conseguida en el aprendizaje con unos datos de prueba para
		 * calcular el porcentaje de efectividad del perceptron
		 * 
		 * @return porcentaje de efectividad del perceptron
		 */
		public double porcentajeAcierto() {
			int count = 0;
			for (int i = 0; i < dataSetTest.length; i++) {
				int value = hardLimit(calculeYValue(dataSetTest[i][0], dataSetTest[i][1], dataSetTest[i][2],
						dataSetTest[i][3], dataSetTest[i][5], dataSetTest[i][5]));
				if (value == dataSetTest[i][6]) {
					count++;
				}
			}
			return (count / dataSetTest.length) * 100;
		}

		/**
		 * Calcula el valor y evaluado en la ecuacion de aprendizaje Los parametros son
		 * cada uno de los valores por los cuales se multiplican los pesos
		 * 
		 * @param x1
		 * @param x2
		 * @param x3
		 * @param x4
		 * @param x5
		 * @param x6
		 * @return el valor de y evaluado en la ecuacion de aprendizaje
		 */
		private double calculeYValue(int x1, int x2, int x3, int x4, int x5, int x6) {
			return x1 * pesoX1 + x2 * pesoX2 + x3 * pesoX3 + x4 * pesoX4 + x5 * pesoX5 + x6 * pesoX6 - baias;
		}

		/**
		 * Recorre el data set y va calculando valores y de cada dato del data set de
		 * entrenamiento y modifica los valores de los pesos y el baias si en algun
		 * momento se presenta un error al evaluar el aprendizaje
		 */
		private void evaluarPesos() {

			for (int i = 0; i < dataSet.length; i++) {

				double y = calculeYValue(dataSet[i][0], dataSet[i][1], dataSet[i][2], dataSet[i][3], dataSet[i][4],
						dataSet[i][5]);

				int hardLimit = hardLimit(y);

				int error = dataSet[i][6] - hardLimit;

				double delta1 = APRENDIZAJE * error * dataSet[i][0];
				double delta2 = APRENDIZAJE * error * dataSet[i][1];
				double delta3 = APRENDIZAJE * error * dataSet[i][2];
				double delta4 = APRENDIZAJE * error * dataSet[i][3];
				double delta5 = APRENDIZAJE * error * dataSet[i][4];
				double delta6 = APRENDIZAJE * error * dataSet[i][5];

				pesoX1 = pesoX1 + delta1;
				pesoX2 = pesoX2 + delta2;
				pesoX3 = pesoX3 + delta3;
				pesoX4 = pesoX4 + delta4;
				pesoX5 = pesoX5 + delta5;
				pesoX6 = pesoX6 + delta6;
				baias = baias - (APRENDIZAJE * error);

				textPerceptron(dataSet[i][0], dataSet[i][1], dataSet[i][2], dataSet[i][3], dataSet[i][4], dataSet[i][5]);
			}
		}

		/**
		 * Se encarga de concatenar y mostar por consola la ecuacion de el perceptron
		 */
		public void mostrarEcuaciones() {
			System.out.println("Ecuacion de la neurona 1: (" + pesoX1 + " * X1 + " + pesoX2 + " * X2 + " + pesoX3
					+ " * X3 + " + pesoX4 + " * X4 + " + pesoX5 + " * X5 + " + pesoX6 + " * X6 - " + baias);
		}

		/**
		 * Se encarga de evaluar si una serie de caracteristicas ingresadas corresponden
		 * a un hombre o unna mujer, de acuerdo con el aprendizaje la funcion de
		 * activacion y la ecuacion del perceptron En otras palabras es la neurona de
		 * salida ya formateada
		 * 
		 * Los parametros son las caracteristicas a evaluar
		 * 
		 * @param x1
		 * @param x2
		 * @param x3
		 * @param x4
		 * @param x5
		 * @param x6
		 * @return hombre o mujer, segun sea el caso
		 */
		public String textPerceptron(int x1, int x2, int x3, int x4, int x5, int x6) {
			return (hardLimit(calculeYValue(x1, x2, x3, x4, x5, x6)) == 1) ? "Hombre" : "Mujer";
		}

		public static void main(String[] args) {
			Perceptron perceptron = new Perceptron();
			perceptron.aprendizaje(100);
			perceptron.mostrarEcuaciones();
			System.out.println("porcentaje de efectividad :" + perceptron.porcentajeAcierto() + "%");
			System.out.println(perceptron.textPerceptron(1, 1, 1, 0, 1, 0)); //Hombre
			System.out.println(perceptron.textPerceptron(0, 1, 0, 1, 0, 1)); // Mujer
		}
	

}
