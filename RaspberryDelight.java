public class RaspberryDelight {
	public int toasts(int upper_limit, int layer_count){
		int applications = 0;
		while (layer_count > 0){
			layer_count = layer_count - upper_limit;
			applications++;
		}
		return applications;
	}
}