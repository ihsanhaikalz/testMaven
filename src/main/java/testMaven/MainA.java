//package testMaven;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.security.NoSuchAlgorithmException;
//import java.util.Arrays;
//import java.util.Random;
//
//import org.eclipse.core.runtime.Assert;
//
////import com.xiaomi.infra.ec.CodecInterface;
////import com.xiaomi.infra.ec.CodecUtils;
////import com.xiaomi.infra.ec.ErasureCodec;
////import com.xiaomi.infra.ec.ErasureCodec.Algorithm;
////import com.xiaomi.infra.ec.ErasureCodec.Builder;
//
//import de.hpi.cloudraid.erasure.jerasure.Decoder;
//import de.hpi.cloudraid.erasure.jerasure.Encoder;
//import de.hpi.cloudraid.erasure.jerasure.ErasureEncodingResult;
//
//public class MainA {
//	private static final Random RNG = new Random();
//	public final static int length = 1_000_000;
//	static File file = null;
//	int k,m,w;
//
//	public static void main(String[] args) throws IOException{
//		// TODO Auto-generated method stub
//		for(int i=0; i<100 ; i++){
//			System.out.println(i);
//			createTempFile();
//			jerasureHPITestFile();
//			jerasureHPITestFIS();
//		}
//	}
//
//	public static void createTempFile(){
//		final byte[] bytes = new byte[length];
//		RNG.nextBytes(bytes);
//		try {
//			// Create temporary file with random bytes of size length
//			file = File.createTempFile("tempfile", null);
//			FileOutputStream fos = new FileOutputStream(file);
//			fos.write(bytes);
//			fos.close();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//	}
//
//	public static void jerasureHPITestFile() throws IOException{
//		long fileSize = file.length();
//		Encoder encoder = new Encoder("SHA-512",2, 2, 4);
//		encoder.encode(file);
//		Decoder decoder = new Decoder(file, 2, 2, 4);
//		decoder.decode(fileSize);
//	}
//
//
//	public static void jerasureHPITestFIS() throws IOException{
//		long fileSize = file.length();
//		Encoder encoder = new Encoder("SHA-512",2, 2, 4);
//		FileInputStream fis = new FileInputStream(file);
//		FileOutputStream fos = new FileOutputStream(file);
//		ErasureEncodingResult eer= encoder.encode(fis, fileSize);
//		Decoder decoder = new Decoder(2, 2, 4, eer);
//		decoder.decode(fos);
//	}
////
////
////
////
////
////
////
////	private static void runTest(CodecInterface codec, int k, int m, int size,
////			boolean printMatrix) {
////		long t1 = System.currentTimeMillis();
////		Random random = new Random();
////		// Generate data
////		byte[][] data = new byte[k][size];
////		byte[][] copiedData = new byte[k][size];
////		for (int r = 0; r < data.length; ++r) {
////			random.nextBytes(data[r]);
////			System.arraycopy(data[r], 0, copiedData[r], 0, data[r].length);
////		}
////		System.out.println("Original data matrix:");
////		CodecUtils.printMatrix(data, printMatrix);
////		long t2 = System.currentTimeMillis();
////
////		// Encode the data
////		byte[][] coding = codec.encode(data);
////		byte[][] copiedCoding = new byte[coding.length][coding[0].length];
////		for (int r = 0; r < coding.length; ++r) {
////			System.arraycopy(coding[r], 0, copiedCoding[r], 0, coding[r].length);
////		}
////		System.out.println("Original coding matrix:");
////		CodecUtils.printMatrix(coding, printMatrix);
////		long t3 = System.currentTimeMillis();
////
////		// Erasure m random blocks
////		int erasures[] = new int[m];
////		int erasured[] = new int[k + m];
////		for (int i = 0; i < m;) {
////			int randomNum = random.nextInt(k + m);
////			erasures[i] = randomNum;
////
////			if (erasured[erasures[i]] == 0) {
////				erasured[erasures[i]] = 1;
////
////				if (erasures[i] < k) {
////					Arrays.fill(data[erasures[i]], 0, data[0].length, (byte)0);
////				} else {
////					Arrays.fill(coding[erasures[i] - k], 0, data[0].length, (byte)0);
////				}
////				++i;
////			}
////		}
////		System.out.println("Erasures matrix:");
////		CodecUtils.printMatrix(erasures, 1, erasures.length, printMatrix);
////		System.out.println("Erasured data matrix:");
////		CodecUtils.printMatrix(data, printMatrix);
////		System.out.println("Erasured coding matrix:");
////		CodecUtils.printMatrix(coding, printMatrix);
////		long t4 = System.currentTimeMillis();
////
////		// Decode data
////		codec.decode(erasures, data, coding);
////		System.out.println("Decoded data matrix:");
////		CodecUtils.printMatrix(data, printMatrix);
////		System.out.println("Decoded coding matrix:");
////		CodecUtils.printMatrix(coding, printMatrix);
////		long t5 = System.currentTimeMillis();
////
////		System.out.println("====Time Stats====");
////		System.out.printf("Generate data:\t%d\n", (t2 - t1));
////		System.out.printf("Encode data:\t%d\n", (t3 - t2));
////		System.out.printf("Erasure data:\t%d\n", (t4 - t3));
////		System.out.printf("Decode data:\t%d\n\n", (t5 - t4));
////
////		// Check result
////		//		    Assert.assertArrayEquals(copiedData, data);
////		//		    Assert.assertArrayEquals(copiedCoding, coding);
////	}
//
//}
