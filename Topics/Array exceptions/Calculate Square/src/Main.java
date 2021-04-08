class FixingExceptions {

    public static void calculateSquare(int[] array, int index) {
        try {
            int result = array[index] * array[index];
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Exception!");
        }

    }
}