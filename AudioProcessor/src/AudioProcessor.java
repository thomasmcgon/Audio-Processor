import java.util.*;

public class AudioProcessor {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		//Asking and taking in file name
		System.out.print("Enter wav file name(Pathway): ");
		String file = in.next();
		double[] samples = StdAudio.read(file);
		
		//declaring input here so the while loop will work
		String input = "";
		
		//While loop that keeps asking for input until "q" is given to exit
		while (!input.equals("q")) {
			System.out.print("Select command (p, r, s, n, v, o, q): ");
			input = in.next();
			//Plays audio
			if (input.equals("p")) {
				System.out.print("\tPlaying sound\n");
				StdAudio.play(samples);
			//Reverses audio
			} else if (input.equals("r")) {
				for(int i = 0;i < samples.length / 2; ++i) {
					double temp = samples[i];
					samples[i] = samples[samples.length - i - 1];
					samples[samples.length -i - 1] = temp;
				}
			//Speeds up audio
			}else if (input.equals("s")) {
				System.out.print("\tSpeed up by how much? ");
				double factor = in.nextDouble();
				System.out.print("\tSpeeding up sound\n");
				double[] speedUp = new double[(int)(samples.length / factor)];
				for(int i = 0; i < speedUp.length; ++i) {
					speedUp[i] = samples[(int) (i* factor)];
				}
				samples = speedUp;
			//Adds Noise to audio
			}else if(input.equals("n")) {
				System.out.print("\tAdd how much noise? ");
				double noise = in.nextDouble();
				System.out.print("\tAdding noise\n");
				for(int i = 0; i < samples.length; ++i) {
					double random = Math.random() * 2*noise - noise;
					samples[i] += random; 
					if (samples[i] < -1) {
						samples[i] = -1;
					}
					else if (samples[i] > 1) {
						samples[i] = 1;
					}
				}
			//Volume controller
			}else if (input.equals("v")) {
				System.out.print("\tScale volume by how much? ");
				double volume = in.nextDouble();
				System.out.print("\tScaling volume\n");
				for(int i = 0; i < samples.length; ++i) {
					samples[i] *= volume;
					if (samples[i] < -1) {
						samples[i] = -1;
					}
					else if (samples[i] > 1) {
						samples[i] = 1;
					}
				}
			//Saves file
			}else if (input.equals("o")) {
				System.out.print("\tSave to what file name? ");
				String saveFile = in.next();
				System.out.print("\tSaving file\n");
				StdAudio.save(saveFile, samples);
			//Quits program
			}else {
				
			}	
		}in.close();		
	}		
}
