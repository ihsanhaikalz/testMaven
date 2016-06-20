package testMaven;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class aspect {

	final int length = 1_000_000;
	final File erasure = new File(String.format("target/result Aspect Measure Erasure %d .csv", length));
	//	final File FileDownloadCSV = new File(String.format("target/result Aspect Download %d Disable Encryption %b.csv", length, disableEncrypt));
	SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	int i = 0;
	public long start, end, duration, durationEncodeFile, durationDecodeFile, durationEncodeFIS, durationDecodeFOS;


	public long getDurationEncodeFile() {
		return durationEncodeFile;
	}

	public void setDurationEncodeFile(long durationEncodeFile) {
		this.durationEncodeFile = durationEncodeFile;
	}

	public long getDurationDecodeFile() {
		return durationDecodeFile;
	}

	public void setDurationDecodeFile(long durationDecodeFile) {
		this.durationDecodeFile = durationDecodeFile;
	}

	public long getDurationEncodeFIS() {
		return durationEncodeFIS;
	}

	public void setDurationEncodeFIS(long durationEncodeFIS) {
		this.durationEncodeFIS = durationEncodeFIS;
	}

	public long getDurationDecodeFOS() {
		return durationDecodeFOS;
	}

	public void setDurationDecodeFOS(long durationDecodeFOS) {
		this.durationDecodeFOS = durationDecodeFOS;
	}

	@Around("execution(* de.hpi.cloudraid.erasure.jerasure.Encoder.encode(File))")
	public Object CalculateEncode(ProceedingJoinPoint joinPoint) throws Throwable{
		start = System.nanoTime();
		Object result = joinPoint.proceed();
		end = System.nanoTime();
		duration = end-start;
		setDurationEncodeFile(duration);
		//		System.out.println(start);
		//		System.out.println(end);

		System.out.println(SDF.format( new Date()) + " Elapsed time for file erasure (ns)= "+ duration);
		return result;
	}

	@Around("execution(*  de.hpi.cloudraid.erasure.jerasure.Decoder.decode(long))")
	public Object CalculateDecode(ProceedingJoinPoint joinPoint) throws Throwable{
		start = System.nanoTime();
		Object result = joinPoint.proceed();
		end = System.nanoTime();
		duration = end-start;
		setDurationDecodeFile(duration);
		//		System.out.println(start);
		//		System.out.println(end);

		System.out.println(SDF.format( new Date()) + " Elapsed time for file deerasure (ns)= "+ duration);
		return result;
	}


	@Around("execution(* de.hpi.cloudraid.erasure.jerasure.Encoder.encode(InputStream, long))")
	public Object CalculateEncodeFIS(ProceedingJoinPoint joinPoint) throws Throwable{
		start = System.nanoTime();
		Object result = joinPoint.proceed();
		end = System.nanoTime();
		duration = end-start;
		setDurationEncodeFIS(duration);
		//		System.out.println(start);
		//		System.out.println(end);

		System.out.println(SDF.format( new Date()) + " Elapsed time for file erasure with FIS (ns)= "+ duration);
		return result;
	}

	@Around("execution(*  de.hpi.cloudraid.erasure.jerasure.Decoder.decode(OutputStream))")
	public Object CalculateDecodeFOS(ProceedingJoinPoint joinPoint) throws Throwable{
		start = System.nanoTime();
		Object result = joinPoint.proceed();
		end = System.nanoTime();
		duration = end-start;
		setDurationDecodeFOS(duration);
		//		System.out.println(start);
		//		System.out.println(end);

		System.out.println(SDF.format( new Date()) + " Elapsed time for file derasure with FOS (ns)= "+ duration);
		if(!erasure.exists()){
			log();
		}
		else{
			logAppend();
		}
		return result;
	}


	public void log(){

		try (PrintWriter d = new PrintWriter(new BufferedOutputStream(new FileOutputStream(erasure, false)))){
			d.println("Encode File; Decode File; Encode FIS; Decode FOS;");
			d.println(String.format("%d;%d;%d;%d", getDurationEncodeFile(), getDurationDecodeFile(), getDurationEncodeFIS(), getDurationDecodeFOS()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void logAppend(){

		try (PrintWriter d = new PrintWriter(new BufferedOutputStream(new FileOutputStream(erasure, true)))){
			d.println(String.format("%d;%d;%d;%d", getDurationEncodeFile(), getDurationDecodeFile(), getDurationEncodeFIS(), getDurationDecodeFOS()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
