
import java.util.*;
import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.IOException;

// Run length encoding implementation for String,String List, Integer :-

class RLE_Test_Method {
	int count = 1;
	String compressedValue;
	String decompressedValue = "";
	ArrayList<String> secquence_string_arraylist_rle_for_result_compressed = new ArrayList<>();
	ArrayList<String> secquence_string_arraylist_rle_for_result_decompressed = new ArrayList<>();
	int printValue = 1;
	char currVal;
	String valueInStr = "";
	int currCharValue = 0;
	int nextCharValue = 0;
	ArrayList<Integer> data_integers_count = new ArrayList<>();

	// COMPRESSION ---

	// Compressing the String values
	// -------------------------------------------------------------------------------------------------------------------------------------------

	String rle_compress_method(String data_string_rle) {
		compressedValue = "";
		currCharValue = 0;
		nextCharValue = 0;
		data_string_rle += " ";
		for (int iterator = 0; iterator < data_string_rle.length() - 1; iterator++) {

			if ((iterator + 1) < (data_string_rle.length())) {
				currCharValue = (char) data_string_rle.charAt(iterator);
				nextCharValue = (char) data_string_rle.charAt(iterator + 1);

				if (currCharValue == nextCharValue) {
					count++;
				} else {
					if (count <= 1) {
						compressedValue += "" + data_string_rle.charAt(iterator);
						count = 1;
					} else {
						compressedValue += "" + data_string_rle.charAt(iterator) + "" + count;
						count = 1;
					}
				}

			}
		}
		return compressedValue;
	}

	// Compressing the String List values
	// ---------------------------------------------------------------------------------------------------------------------------------------

	ArrayList<String> rle_compress_method(ArrayList<String> data_array_string_rle) {
		compressedValue = "";

		for (String value : data_array_string_rle) {

			secquence_string_arraylist_rle_for_result_compressed.add(rle_compress_method(value));
		}
		return secquence_string_arraylist_rle_for_result_compressed;
	}

	// Compressing the Integer values
	// -------------------------------------------------------------------------------------------------------------------------------------------

	int rle_compress_method_variation(int data_integers_rle) {

		compressedValue = "";
		int currcharValue = 0;
		int nextcharValue = 0;
		int splitted_integer_rle;
		int Count = 1;
		ArrayList<Integer> return_array_integer = new ArrayList<>();
		int data_integers_temp = data_integers_rle;

		while (data_integers_temp != 0) {

			splitted_integer_rle = data_integers_temp % 10;
			return_array_integer.add(splitted_integer_rle);
			data_integers_temp = (data_integers_temp - splitted_integer_rle) / 10;

		}

		Collections.reverse(return_array_integer);

		for (int iteratorIdx = 1; iteratorIdx <= return_array_integer.size(); iteratorIdx++) {

			if ((iteratorIdx) < (String.valueOf(data_integers_rle).length())) {
				currcharValue = return_array_integer.get(iteratorIdx - 1);

				nextcharValue = return_array_integer.get(iteratorIdx);

				if (currcharValue == nextcharValue) {
					Count++;

				}

				else {

					if (Count <= 1) {
						data_integers_count.add(Count);
						Count = 1;
					} else {
						compressedValue += return_array_integer.get(iteratorIdx - 1);

						data_integers_count.add(Count);

						Count = 1;
					}

				}

			}

		}
		compressedValue += return_array_integer.get(return_array_integer.size() - 1);

		data_integers_count.add(Count);

		Count = 1;

		return Integer.parseInt(compressedValue);
	}

	// DECOMPRESSION ---

	// Decompressing the Integer values
	// -------------------------------------------------------------------------------------------------------------------------------------------

	int rle_decompress_method_variation(int data_compressed_integer_rle,
			ArrayList<Integer> data_compressed_integer_array_rle) {
		decompressedValue = "";

		String data_compressed_integer_rle_temp = "" + data_compressed_integer_rle;

		for (int for_iterator = 0; for_iterator < data_compressed_integer_rle_temp.length(); for_iterator++) {

			for (int print_value = 0; print_value < data_compressed_integer_array_rle
					.get(for_iterator); print_value++) {

				decompressedValue += data_compressed_integer_rle_temp.charAt(for_iterator);
			}
		}
		return Integer.parseInt(decompressedValue);
	}

	// Decompressing the String values
	// -------------------------------------------------------------------------------------------------------------------------------------------

	String rle_decompress_method(String data_string_for_compress_rle) {
		data_string_for_compress_rle += "1";
		decompressedValue = "";
		char currVal;
		int printValue = 0;
		String valueInStr = "";

		for (int iteratorValue = 0; iteratorValue < data_string_for_compress_rle.length(); iteratorValue++) {
			currVal = data_string_for_compress_rle.charAt(iteratorValue); 

			if ((data_string_for_compress_rle.codePointAt(iteratorValue) > 64)
					&& (data_string_for_compress_rle.charAt(iteratorValue) < 123)) {
				valueInStr = "";
				if (data_string_for_compress_rle.codePointAt(iteratorValue + 1) <= 57) {
					while ((data_string_for_compress_rle.codePointAt(iteratorValue + 1) <= 57)
							&& (iteratorValue < data_string_for_compress_rle.length() - 2)) {
						valueInStr += data_string_for_compress_rle.charAt(iteratorValue + 1);
						printValue = Integer.parseInt(valueInStr);
						iteratorValue++;
					}

					for (int prtVal = 0; prtVal < printValue; prtVal++) {
						decompressedValue += currVal;
					}
					printValue = 1;
				} else {
					decompressedValue += currVal;
				}
			} else {
				continue;
			}
		}
		return decompressedValue;
	}

	// Decompressing the String List values
	// -----------------------------------------------------------------------------------------------------------------------------------------

	ArrayList<String> rle_decompress_method(ArrayList<String> data_array_string_decompress_rle) {
		String requiredValue;
		printValue = 0;
		for (String value : data_array_string_decompress_rle) {

			requiredValue = rle_decompress_method(value);
			secquence_string_arraylist_rle_for_result_decompressed
					.add(requiredValue.substring(0, requiredValue.length()));
		}
		return secquence_string_arraylist_rle_for_result_decompressed;
	}

}

// Delta encoding implementation for String,String List,Integer,Integer
// List,Integer Array :-

class Delta_Test_Method {
	long consecutive_integers_results = 0;
	int intialDelta = 0;
	int delta = 0;

	// COMPRESSION ---

	// Compressing the String values
	// -------------------------------------------------------------------------------------------------------------------------------------------

	String delta_compress_method(String data_string_de) {
		intialDelta = data_string_de.codePointAt(0) - data_string_de.codePointAt(1);
		delta = 0;
		for (int val = 1; val < data_string_de.length() - 1; val++) {
			delta = data_string_de.codePointAt(val) - data_string_de.codePointAt(val + 1);

			if (delta == intialDelta) {
				continue;
			} else {
				return "Not Suitable Format";
			}
		}

		return "" + data_string_de.charAt(0) + data_string_de.charAt(data_string_de.length() - 1) + intialDelta;
	}

	// Compressing the String List values
	// -------------------------------------------------------------------------------------------------------------------------------------------

	ArrayList<String> delta_compress_method(ArrayList<String> data_array_string_de) {
		ArrayList<String> consecutive_string_arraylist_de_for_result = new ArrayList<>();
		for (String words : data_array_string_de) {

			consecutive_string_arraylist_de_for_result.add(delta_compress_method(words));
		}

		return consecutive_string_arraylist_de_for_result;

	}

	// Compressing the Integer values
	// -------------------------------------------------------------------------------------------------------------------------------------------

	int delta_compress_method(int data_integers_de) {
		ArrayList<Integer> integer_holder_arraylist_de = new ArrayList<>();
		int splitted_integer_de = 0;

		while (data_integers_de != 0) {
			splitted_integer_de = data_integers_de % 10;
			data_integers_de = (data_integers_de - splitted_integer_de) / 10;

			integer_holder_arraylist_de.add(splitted_integer_de);
		}

		intialDelta = integer_holder_arraylist_de.get(0) - integer_holder_arraylist_de.get(1);
		delta = 0;

		for (int digit = 1; digit < integer_holder_arraylist_de.size() - 1; digit++) {
			delta = integer_holder_arraylist_de.get(digit) - integer_holder_arraylist_de.get(digit + 1);

			if (delta == intialDelta) {
				continue;
			} else {
				return 0;
			}
		}
		return (integer_holder_arraylist_de.get(integer_holder_arraylist_de.size() - 1) * 100)
				+ (integer_holder_arraylist_de.get(0) * 10) + (intialDelta);

	}

	// Compressing the Integer List values
	// -------------------------------------------------------------------------------------------------------------------------------------------

	ArrayList<Integer> delta_compress_method_variation(ArrayList<Integer> data_array_integers_de) {
		ArrayList<Integer> consecutive_integer_arraylist_de_for_result = new ArrayList<>();

		for (int integerVal : data_array_integers_de) {
			consecutive_integer_arraylist_de_for_result.add(delta_compress_method(integerVal));
		}
		return consecutive_integer_arraylist_de_for_result;
	}

	// Compressing the Integer Array values
	// -------------------------------------------------------------------------------------------------------------------------------------------

	String delta_compress_method_variation_2(int[] array_of_integers_de) {

		int firstvalue = array_of_integers_de[0];
		int lastvalue = array_of_integers_de[1];

		intialDelta = firstvalue - lastvalue;

		for (int ditVal = 1; ditVal < array_of_integers_de.length - 1; ditVal++) {

			delta = array_of_integers_de[ditVal] - array_of_integers_de[ditVal + 1];

			if (delta == intialDelta) {
				continue;
			} else {
				return "Not a Suitable Format";
			}
		}

		return "" + firstvalue + "_" + array_of_integers_de[array_of_integers_de.length - 1] + "," + delta;
	}

	// Compressing the String Values - Non Consecutive values
	// -------------------------------------------------------------------------------------------------------------------------------------------

	ArrayList<Byte> delta_compress_method_non_consecutive(char[] data_non_consecutive_de) {
		ArrayList<Byte> temp_non_consecutive_string_de = new ArrayList<>();

		byte delta_value = 0;
		temp_non_consecutive_string_de.add((byte) (data_non_consecutive_de[0]));

		for (int chValue = 1; chValue < data_non_consecutive_de.length; chValue++) {

			delta_value = (byte) (data_non_consecutive_de[chValue - 1] - data_non_consecutive_de[chValue]);
			temp_non_consecutive_string_de.add(delta_value);
		}

		return temp_non_consecutive_string_de;
	}

	// Compressing the Integer Values - Non Consecutive Values
	// -------------------------------------------------------------------------------------------------------------------------------------------
	int[] delta_compress_method_non_consecutive(int[] data_non_consecutive_integer_array) {

		int[] delta_compressed_integer_array = new int[data_non_consecutive_integer_array.length];
		delta_compressed_integer_array[0] = data_non_consecutive_integer_array[0];
		int delta_value_integer;

		for (int arrIdx = 1; arrIdx < data_non_consecutive_integer_array.length; arrIdx++) {

			delta_value_integer = data_non_consecutive_integer_array[arrIdx]
					- data_non_consecutive_integer_array[arrIdx - 1];

			delta_compressed_integer_array[arrIdx] = delta_value_integer;
		}

		return delta_compressed_integer_array;
	}

	// DECOMPRESSION ---

	// Decompressing the String values
	// -------------------------------------------------------------------------------------------------------------------------------------------

	String delta_decompress_method(String data_string_for_compress_de) {
		int firstValue = data_string_for_compress_de.codePointAt(0);
		int lastValue = data_string_for_compress_de.codePointAt(1);
		int deltaDifference = 0;
		String decompressedvalue = "";
		if (data_string_for_compress_de.length() == 4) {
			deltaDifference = Integer
					.parseInt(data_string_for_compress_de.charAt(2) + "" + data_string_for_compress_de.charAt(3));

		} else {
			deltaDifference = Integer.parseInt("" + data_string_for_compress_de.charAt(2));
		}

		if (deltaDifference > 0) {
			for (int fvChar = firstValue; fvChar <= lastValue; fvChar += deltaDifference) {
				decompressedvalue += (char) fvChar;

			}
		} else if (deltaDifference < 0) {
			for (int fvChar = firstValue; fvChar <= lastValue; fvChar -= deltaDifference) {
				decompressedvalue += (char) fvChar;

			}
		}
		return decompressedvalue;
	}

	// Decompressing the String List values
	// -------------------------------------------------------------------------------------------------------------------------------------------

	ArrayList<String> delta_decompress_method(ArrayList<String> data_arraylist_string_compress_de) {
		ArrayList<String> consecutive_string_arraylist_de_for_result_compress = new ArrayList<>();
		for (String words : data_arraylist_string_compress_de) {

			consecutive_string_arraylist_de_for_result_compress.add(delta_decompress_method(words));
		}

		return consecutive_string_arraylist_de_for_result_compress;
	}

	// Decmpressing the Integer values
	// -------------------------------------------------------------------------------------------------------------------------------------------

	int delta_decompress_method(int data_integers_compress_de) {

		String delta_string_for_tempUse = "" + data_integers_compress_de;

		int decompressedvalue_integer = Integer.parseInt(delta_decompress_method(delta_string_for_tempUse));

		return decompressedvalue_integer;
	}

	// Decompressing the Integer List values
	// -------------------------------------------------------------------------------------------------------------------------------------------

	ArrayList<Integer> delta_decompress_method_variation(ArrayList<Integer> data_array_integers_compress_de) {
		ArrayList<Integer> consecutive_integer_arraylist_de_for_result_compress = new ArrayList<>();

		for (int integerVal : data_array_integers_compress_de) {

			consecutive_integer_arraylist_de_for_result_compress.add(delta_decompress_method(integerVal));
		}

		return consecutive_integer_arraylist_de_for_result_compress;
	}

	// Decompressing the Integer Array values - Non Consecutive Values
	// -------------------------------------------------------------------------------------------------------------------------------------------

	int[] delta_decompress_method_variation_2(String delta_compressed_string_val) {
		int startIndex = delta_compressed_string_val.indexOf("_");
		int startValue = Integer.parseInt(delta_compressed_string_val.substring(0, startIndex));

		int stopIndex = delta_compressed_string_val.indexOf(",");
		int stopValue = Integer.parseInt(delta_compressed_string_val.substring(startIndex + 1, stopIndex));

		String difference = delta_compressed_string_val.substring(stopIndex + 1, delta_compressed_string_val.length());

		int difference_value = Integer.parseInt(difference);

		if(difference_value < 0){ 
			difference_value = -1*(difference_value);
		};

		int length_of_array = (stopValue) / difference_value; 
		

		int[] decompressed_array_de = new int[length_of_array];

		if (difference_value > 0) {

			for (int digitval = startValue, idx = 0; digitval <= stopValue; digitval += difference_value, idx++) {
				decompressed_array_de[idx] = digitval;
			}
		} else {
			for (int digitval = startValue, idx = 0; digitval <= stopValue; digitval -= difference_value, idx++) {
				decompressed_array_de[idx] = digitval;
			}
		}
		return decompressed_array_de;
	}

	// Decompressing the Character Array values - Non Consecutive Values
	// -------------------------------------------------------------------------------------------------------------------------------------------

	char[] delta_decompress_method_non_consecutive(ArrayList<Byte> data_non_consecutive_de) {
		char[] temp_compressed_non_consecutive_string_de = new char[data_non_consecutive_de.size()];

		int delta = data_non_consecutive_de.get(0);

		temp_compressed_non_consecutive_string_de[0] = (char) delta;

		for (int chValue = 1; chValue < data_non_consecutive_de.size(); chValue++) {

			delta -= data_non_consecutive_de.get(chValue);

			temp_compressed_non_consecutive_string_de[chValue] = (char) delta;
		}

		return temp_compressed_non_consecutive_string_de;
	}

	// Decompressing the Integer Array values
	// -------------------------------------------------------------------------------------------------------------------------------------------

	int[] delta_decompress_method_non_consecutive(int[] data_non_consecutive_integer_array) {

		int[] delta_decompressed_integer_array = new int[data_non_consecutive_integer_array.length];
		delta_decompressed_integer_array[0] = data_non_consecutive_integer_array[0];

		int delta_value_integer = 0;

		for (int arrIdx = 0; arrIdx <= data_non_consecutive_integer_array.length - 1; arrIdx++) {

			delta_value_integer += data_non_consecutive_integer_array[arrIdx];

			delta_decompressed_integer_array[arrIdx] = delta_value_integer;
		}

		return delta_decompressed_integer_array;
	}

}

// Bit Packing implementation for String,Integer :-

class BP_Test_Method {
	int splitted_integer_from_original;
	int char_value_of_element;
	int size_of_the_data = 0;
	int bit_packed_size_of_data = 0;
	boolean[] boolean_array_for_retriving;
	String binary_value = "";

	// Data Stored For Decompression

	ArrayList<int[]> data_for_decompression_int = new ArrayList<int[]>();
	ArrayList<int[]> data_for_decompression_string = new ArrayList<int[]>();
	ArrayList<int[]> data_for_decompression_byte_list = new ArrayList<int[]>();
	ArrayList<int[]> data_for_decompression_char_arr = new ArrayList<int[]>();
	ArrayList<int[]> data_for_decompression_str_list = new ArrayList<int[]>();
	ArrayList<int[]> data_for_decompression_int_list = new ArrayList<int[]>();
	ArrayList<int[]> data_for_decompression_int_arr = new ArrayList<int[]>();

	// COMPRESSION ...
	// Compressing The String Value
	// -------------------------------------------------------------------------------------------------------------------------------------------
	int bitpack_compress_method(String data_string_for_compress_bp) {

		if (!data_for_decompression_string.isEmpty()) {
			data_for_decompression_string.clear();
		}

		size_of_the_data = 0;
		bit_packed_size_of_data = 0;
		binary_value = "";
		Hashtable<Integer, boolean[]> hasttable_for_bitpack = new Hashtable<Integer, boolean[]>();
		for (int iterate = 0; iterate < data_string_for_compress_bp.length(); iterate++) {

			char_value_of_element = (int) data_string_for_compress_bp.charAt(iterate);
			// System.out.println("\n"+char_value_of_element);
			size_of_the_data += 16; // Char take 16 bits

			boolean[] boolean_arrayof_value = intToBoolArray(char_value_of_element, 8);

			hasttable_for_bitpack.put(char_value_of_element, boolean_arrayof_value);

			boolean_array_for_retriving = hasttable_for_bitpack.get(char_value_of_element);
			int[] int_array_retrival = new int[8];
			for (int boolean_idx = 0; boolean_idx < 8; boolean_idx++) {

				if (boolean_array_for_retriving[boolean_idx] == true) {
					int_array_retrival[boolean_idx] = 1;
				} else {
					int_array_retrival[boolean_idx] = 0;
				}
			}

			data_for_decompression_string.add(int_array_retrival);

			for (int i = 0; i < 8; i++) {
				// System.out.println((boolean_array_for_retriving[i] == true)? 1 : 0);
				bit_packed_size_of_data += 1; // Boolen take 1 bit
			}

		}
		return bit_packed_size_of_data;
	}

	// Compressing The Integer Value
	// -------------------------------------------------------------------------------------------------------------------------------------------
	int bitpack_compress_method(int data_integer_for_compress_bp) {
		size_of_the_data = 0;
		bit_packed_size_of_data = 0;
		int data_integer_for_compress_temp_bp = data_integer_for_compress_bp;

		Hashtable<Integer, boolean[]> hasttable_for_bitpack = new Hashtable<Integer, boolean[]>();

		for (int iterate = 0; iterate < String.valueOf(data_integer_for_compress_bp).length(); iterate++) {

			splitted_integer_from_original = data_integer_for_compress_temp_bp % 10;
			data_integer_for_compress_temp_bp = (data_integer_for_compress_temp_bp - splitted_integer_from_original)
					/ 10;

			char_value_of_element = (int) splitted_integer_from_original;
			// System.out.println("\n"+char_value_of_element);
			size_of_the_data += 16;

			boolean[] boolean_arrayof_value = intToBoolArray(char_value_of_element, 8);
			hasttable_for_bitpack.put(char_value_of_element, boolean_arrayof_value);
			boolean[] boolean_array_for_retriving;

			boolean_array_for_retriving = hasttable_for_bitpack.get(char_value_of_element);

			for (int i = 0; i < 8; i++) {
				// System.out.print((boolean_array_for_retriving[i] == true)? 1 : 0);
				bit_packed_size_of_data += 1;
			}

		}
		return bit_packed_size_of_data;
	}

	// Compressing The String List Value
	// -------------------------------------------------------------------------------------------------------------------------------------------

	int bitpack_compress_method(ArrayList<String> data_string_for_compress_bp_list) {

		for (String data_string_for_compress_bp : data_string_for_compress_bp_list) {

			size_of_the_data = 0;
			bit_packed_size_of_data = 0;
			Hashtable<Integer, boolean[]> hasttable_for_bitpack = new Hashtable<Integer, boolean[]>();
			for (int iterate = 0; iterate < data_string_for_compress_bp.length(); iterate++) {

				char_value_of_element = (int) data_string_for_compress_bp.charAt(iterate);
				// System.out.println("\n"+char_value_of_element);
				size_of_the_data += 16; // Char take 16 bits

				boolean[] boolean_arrayof_value = intToBoolArray(char_value_of_element, 8);
				hasttable_for_bitpack.put(char_value_of_element, boolean_arrayof_value);

				boolean_array_for_retriving = hasttable_for_bitpack.get(char_value_of_element);

				for (int i = 0; i < 8; i++) {
					// System.out.println((boolean_array_for_retriving[i] == true)? 1 : 0);
					bit_packed_size_of_data += 1; // Boolen take 1 bit
				}

			}

		}
		return bit_packed_size_of_data;
	}

	// Compressing The Integer Array Value
	// -------------------------------------------------------------------------------------------------------------------------------------------

	int bitpack_compress_method_arr(int[] data_integer_for_compress_bp_list) {

		for (int data_string_for_compress_bp : data_integer_for_compress_bp_list) {

			int data_integer_for_compress_temp_bp = data_string_for_compress_bp;
			Hashtable<Integer, boolean[]> hasttable_for_bitpack = new Hashtable<Integer, boolean[]>();
			for (int iterate = 0; iterate < String.valueOf(data_integer_for_compress_bp_list).length(); iterate++) {

				splitted_integer_from_original = data_integer_for_compress_temp_bp % 10;
				data_integer_for_compress_temp_bp = (data_integer_for_compress_temp_bp - splitted_integer_from_original)
						/ 10;

				char_value_of_element = (int) splitted_integer_from_original;
				// System.out.println("\n"+char_value_of_element);
				size_of_the_data += 16;

				boolean[] boolean_arrayof_value = intToBoolArray(char_value_of_element, 8);
				hasttable_for_bitpack.put(char_value_of_element, boolean_arrayof_value);
				boolean[] boolean_array_for_retriving;

				boolean_array_for_retriving = hasttable_for_bitpack.get(char_value_of_element);

				for (int i = 0; i < 8; i++) {
					// System.out.print((boolean_array_for_retriving[i] == true)? 1 : 0);
					bit_packed_size_of_data += 1;
				}

			}
		}
		return bit_packed_size_of_data;
	}

	// Compressing The Byte List Value
	// -------------------------------------------------------------------------------------------------------------------------------------------

	int bitpack_compress_method_arr(ArrayList<Byte> data_character_for_compress_bp_list) {

		for (byte data_string_for_compress_bp : data_character_for_compress_bp_list) {

			size_of_the_data = 0;
			bit_packed_size_of_data = 0;
			Hashtable<Integer, boolean[]> hasttable_for_bitpack = new Hashtable<Integer, boolean[]>();
			for (int iterate = 0; iterate < String.valueOf(data_string_for_compress_bp).length(); iterate++) {

				char_value_of_element = (int) data_string_for_compress_bp;
				// System.out.println("\n"+char_value_of_element);
				size_of_the_data += 16; // Char take 16 bits

				boolean[] boolean_arrayof_value = intToBoolArray(char_value_of_element, 8);
				hasttable_for_bitpack.put(char_value_of_element, boolean_arrayof_value);

				boolean_array_for_retriving = hasttable_for_bitpack.get(char_value_of_element);

				for (int i = 0; i < 8; i++) {
					// System.out.println((boolean_array_for_retriving[i] == true)? 1 : 0);
					bit_packed_size_of_data += 1; // Boolen take 1 bit
				}

			}

		}
		return bit_packed_size_of_data;
	}

	// Compressing The Integer List Value
	// -------------------------------------------------------------------------------------------------------------------------------------------

	int bitpack_compress_method_int(ArrayList<Integer> data_string_for_compress_bp_int_list) {

		for (int data_integer_for_compress_bp : data_string_for_compress_bp_int_list) {
			int data_integer_for_compress_temp_bp = data_integer_for_compress_bp;
			Hashtable<Integer, boolean[]> hasttable_for_bitpack = new Hashtable<Integer, boolean[]>();
			for (int iterate = 0; iterate < String.valueOf(data_integer_for_compress_bp).length(); iterate++) {

				splitted_integer_from_original = data_integer_for_compress_temp_bp % 10;
				data_integer_for_compress_temp_bp = (data_integer_for_compress_temp_bp - splitted_integer_from_original)
						/ 10;

				char_value_of_element = (int) splitted_integer_from_original;
				// System.out.println("\n"+char_value_of_element);
				size_of_the_data += 16;

				boolean[] boolean_arrayof_value = intToBoolArray(char_value_of_element, 8);
				hasttable_for_bitpack.put(char_value_of_element, boolean_arrayof_value);
				boolean[] boolean_array_for_retriving;

				boolean_array_for_retriving = hasttable_for_bitpack.get(char_value_of_element);

				for (int i = 0; i < 8; i++) {
					// System.out.print((boolean_array_for_retriving[i] == true)? 1 : 0);
					bit_packed_size_of_data += 1;
				}

			}
		}
		return bit_packed_size_of_data;
	}

	boolean[] intToBoolArray(int value, int size) {
		String binaryString = String.format("%8s", Integer.toBinaryString(value)).replace(' ', '0');

		// Convert binary string to boolean array
		boolean[] boolArray = new boolean[binaryString.length()];
		for (int i = 0; i < binaryString.length(); i++) {
			boolArray[i] = binaryString.charAt(i) == '1';
		}
		return boolArray;
	}

}

class Input_Seeker {

	String content = "";

	BP_Test_Method bit_packing = new BP_Test_Method();
	RLE_Test_Method run_length_encoding = new RLE_Test_Method();
	Delta_Test_Method delta_encoding = new Delta_Test_Method();

	String sequence_string_rle = "";
	int sequence_integer_value_rle = 0;
	String string_value_from_user = "";
	String consecutive_string_de = "";
	int consecutive_integers_de = 0;
	String sample_string_from_user = "";
	String[] sample_arr_de;
	String[] sample_arr_de_non;
	String[] sample_arr_de_non_int;
	char[] non_consecutive_string_de;
	String source_string_for_bitpack = "";
	int source_integer_for_bitpack = 0;

	void seek_input_and_process(String scheme_name, int type, int method_determiner) throws Exception {
		Scanner get_input_from_user = new Scanner(System.in);
		if (method_determiner == 1) {
			JFileChooser fileChooser = new JFileChooser();

			int result = fileChooser.showOpenDialog(null);

			if (result == JFileChooser.APPROVE_OPTION) {
				try {

					String fileName = fileChooser.getSelectedFile().getPath();

					content = readFileToString(fileName).trim();

				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("File selection canceled by the user.");
			}
		}

		switch (scheme_name) {

			case "RUN LENGTH ENCODING - RLE":
				switch (type) {
					case 1:
						// String sources for Run Length Encoding :

						System.out.print("\033[H\033[2J");
						System.out.flush();
						if (method_determiner == 1) {
							sequence_string_rle = new String(content);
						} else {
							System.out.println("Enter The String Value For Run Length Encoding [Ex: aaaaabbbbccc]: ");
							sequence_string_rle = get_input_from_user.next();
						}

						String compressed_string_rle = run_length_encoding.rle_compress_method(sequence_string_rle);
						String decompressed_string_rle = run_length_encoding
								.rle_decompress_method(compressed_string_rle);

						// Compression STRING percentage calculation :
						int bit_packed_size_string_rle = bit_packing.bitpack_compress_method(compressed_string_rle);
						float compressed_string_percent_rle = (memorySizeOf(sequence_string_rle)
								- (bit_packed_size_string_rle)) * 100 / memorySizeOf(sequence_string_rle);

						System.out.print("\033[H\033[2J");
						System.out.flush();
						System.out.println("\n\u001B[1;30;46mRun Length Encoding (RLE): \u001B[0m\n" +

								"\n\u001B[1;35mString :- \u001B[0m \n" +
								"\nGiven String Value (RLE): " + sequence_string_rle + "\n" +

								"\n\nThe Compressed String Value (RLE): " + compressed_string_rle +

								"\n \nDecompressed String(RLE) : " + decompressed_string_rle +

								"\n \n\u001B[1:35mMore Details :-\n\n" +
								"\u001B[1;31mLength Of String Value (RLE): " + sequence_string_rle.length()
								+ "\u001B[0m \n" +
								"\n\u001B[1;31mMemory Size : " + memorySizeOf(sequence_string_rle) + " bits\u001B[0m\n"
								+

								"\nLength Of Compressed String Value (RLE): " + compressed_string_rle.length() + "\n" +
								"\n\u001B[1;32mMemory Size : " + memorySizeOf(compressed_string_rle)
								+ " bits\u001B[0m\n" +
								"\n\u001B[1;32mBit Packed Size : " + bit_packed_size_string_rle
								+ " bits \u001B[0m\n" +
								"\n\u001B[1;32mString Compression Percentage (RLE): "
								+ (int) compressed_string_percent_rle
								+ "%\u001B[0m \n");

						break;
					case 2:

						System.out.print("\033[H\033[2J");
						System.out.flush();

						// Integer sources for Run LEngth Encoding :
						if (method_determiner == 1) {
							sequence_integer_value_rle = Integer.parseInt(content);
						} else {
							System.out.println("Enter The Integer Value For Run Length Encoding [Ex: 2233444]: ");
							sequence_integer_value_rle = get_input_from_user.nextInt();
						}

						int compressed_integer_array = run_length_encoding
								.rle_compress_method_variation(sequence_integer_value_rle);
						int decompressed_integer_array = run_length_encoding.rle_decompress_method_variation(
								compressed_integer_array,
								run_length_encoding.data_integers_count);

						// Compression Integer percentage calculation :
						int bit_packed_size_integer_rle = bit_packing.bitpack_compress_method(compressed_integer_array);

						float compressed_integer_percent_rle = (memorySizeOf(sequence_integer_value_rle)
								- bit_packed_size_integer_rle) * 100
								/ memorySizeOf(sequence_integer_value_rle);
						System.out.print("\033[H\033[2J");
						System.out.flush();

						System.out.println("\n\u001B[1;30;46mRun Length Encoding (RLE): \u001B[0m\n" +

								"\n\u001B[1;35mInteger :- \u001B[0m \n" +
								"\nGiven Integer Value (RLE): " + sequence_integer_value_rle + "\n" +

								"\n\nThe Compressed Integer Value (RLE): " + compressed_integer_array +

								"\n \nDecompressed Integer(RLE) : " + decompressed_integer_array +

								"\n \n\u001B[1:35mMore Details :-\n\n" +
								"\u001B[1;31mLength Of Integer Value (RLE): "
								+ String.valueOf(sequence_integer_value_rle).length() + "\u001B[0m \n" +
								"\n\u001B[1;31mMemory Size Of The Original Data: "
								+ memorySizeOf(sequence_integer_value_rle) + " bits\u001B[0m\n"
								+

								"\nLength Of Compressed Integer Value (RLE): "
								+ String.valueOf(compressed_integer_array).length() + "\n" +
								"\n\u001B[1;32mMemory Size Of Compressed Data: "
								+ memorySizeOf(compressed_integer_array) + " bits\u001B[0m\n" +
								"\n\u001B[1;32mBit Packed Size : " + bit_packed_size_integer_rle
								+ " bits \u001B[0m\n" +
								"\n\u001B[1;32mInteger Compression Percentage (RLE): "
								+ (int) compressed_integer_percent_rle
								+ "%\u001B[0m \n");
						break;

					case 3:
						// Arraylist (String) for run length encoding :
						System.out.print("\033[H\033[2J");
						System.out.flush();
						if (method_determiner == 1) {
							string_value_from_user = content;
						} else {
							System.out.println(
									"Enter The String List Value For Run Length Encoding [Ex: aabbcc,aaabc,ffggjj] : ");
							string_value_from_user = get_input_from_user.next();
						}

						ArrayList<String> sequence_string_arraylist_rle = new ArrayList<>(
								Arrays.asList(string_value_from_user.split(",")));
						ArrayList<String> compressed_string_arraylist_rle = run_length_encoding
								.rle_compress_method(sequence_string_arraylist_rle);
						ArrayList<String> decompressed_string_arraylist_rle = run_length_encoding
								.rle_decompress_method(compressed_string_arraylist_rle);

						// Compression STRING LIST percentage calculation :
						int bit_packed_size_string_list_rle = bit_packing
								.bitpack_compress_method(compressed_string_arraylist_rle);
						float compressed_arraylist_string_percent_rle = (memorySizeOf(sequence_string_arraylist_rle)
								- bit_packed_size_string_list_rle) * 100
								/ memorySizeOf(sequence_string_arraylist_rle);

						System.out.print("\033[H\033[2J");
						System.out.flush();

						System.out.println("\n\u001B[1;30;46mRun Length Encoding (RLE): \u001B[0m\n" +

								"\n\u001B[1;35mString List :- \u001B[0m \n" +
								"\nGiven String List Value (RLE): " + sequence_string_arraylist_rle + "\n" +

								"\n\nThe Compressed String List Value (RLE): " + compressed_string_arraylist_rle +

								"\n \nDecompressed String List(RLE) : " + decompressed_string_arraylist_rle +

								"\n \n\u001B[1:35mMore Details :-\n\n" +
								"\u001B[1;31mLength Of String List Value (RLE): "
								+ getTotalSize(sequence_string_arraylist_rle) + "\u001B[0m \n" +
								"\n\u001B[1;31mMemory Size Of Original Data : "
								+ memorySizeOf(sequence_string_arraylist_rle) + " bits\u001B[0m\n"
								+

								"\nLength Of Compressed String List Value (RLE): "
								+ getTotalSize(compressed_string_arraylist_rle) + "\n" +
								"\n\u001B[1;32mMemory Size Of Compressed Data : "
								+ memorySizeOf(compressed_string_arraylist_rle)
								+ " bits\u001B[0m\n" +
								"\n\u001B[1;32mBit Packed Size : " + bit_packed_size_string_list_rle
								+ " bits \u001B[0m\n" +
								"\n\u001B[1;32mString List Compression Percentage (RLE): "
								+ (int) compressed_arraylist_string_percent_rle
								+ "%\u001B[0m \n");
						break;
				}
				break;
			case "   DELTA ENCODING - DE":
				switch (type) {

					case 1:
						System.out.print("\033[H\033[2J");
						System.out.flush();

						// String sources for Delta Encoding :
						if (method_determiner == 1) {
							consecutive_string_de = content;
						} else {
							System.out.println("Enter The String Value For Delta Encoding [Ex: abcdefghijkl]: ");
							consecutive_string_de = get_input_from_user.next();
						}
						String compressed_string_de = delta_encoding.delta_compress_method(consecutive_string_de);
						String decompressed_string_de = delta_encoding.delta_decompress_method(compressed_string_de);

						// Compression STRING percentage calculation :
						int bit_packed_size_string_de = bit_packing.bitpack_compress_method(compressed_string_de);

						float compressed_string_percent_de = (memorySizeOf(consecutive_string_de)
								- bit_packed_size_string_de) * 100
								/ memorySizeOf(consecutive_string_de);
						System.out.print("\033[H\033[2J");
						System.out.flush();

						System.out.println("\n\u001B[1;30;46mDelta Encoding (DE): \u001B[0m\n" +
								"\n\u001B[1;34mConsective Values  :- \u001B[0m \n\n" +
								"\n\u001B[1;35mString  :- \u001B[0m \n" +
								"\nGiven String  Value (DE): " + consecutive_string_de + "\n" +

								"\n\nThe Compressed String  Value (DE): " + compressed_string_de +

								"\n \nDecompressed String (DE) : " + decompressed_string_de +

								"\n \n\u001B[1:35mMore Details :-\n\n" +
								"\u001B[1;31mLength Of String  Value (DE): " + consecutive_string_de.length()
								+ "\u001B[0m \n" +
								"\n\u001B[1;31mMemory Size Of Original Data : " + memorySizeOf(consecutive_string_de)
								+ " bits\u001B[0m\n" +

								"\nLength Of Compressed String  Value (DE): " + compressed_string_de.length()
								+ " bits\n" +
								"\n\u001B[1;32mMemory Size Of Compressed Data : " + memorySizeOf(compressed_string_de)
								+ "\u001B[0m\n" +
								"\n\u001B[1;32mBit Packed Size : " + bit_packed_size_string_de
								+ " bits \u001B[0m\n" +
								"\n\u001B[1;32mString  Compression Percentage (DE): "
								+ (int) compressed_string_percent_de
								+ "%\u001B[0m \n");

						break;

					case 2:
						System.out.print("\033[H\033[2J");
						System.out.flush();

						// Integer sources for Delta Encoding :
						if (method_determiner == 1) {
							consecutive_integers_de = Integer.parseInt(content);
						} else {
							System.out.println("Enter The Integer Value For Delta Encoding [Ex: 12345]: ");

							consecutive_integers_de = get_input_from_user.nextInt();
						}

						int compressed_integers_de = delta_encoding.delta_compress_method(consecutive_integers_de);
						int decompressed_integers_de = delta_encoding.delta_decompress_method(compressed_integers_de);

						// Compression Integer percentage calculation :
						int bit_packed_size_integer_de = bit_packing.bitpack_compress_method(compressed_integers_de);

						float compressed_integers_percent_de = (memorySizeOf(consecutive_integers_de)
								- bit_packed_size_integer_de) * 100
								/ memorySizeOf(consecutive_integers_de);
						System.out.print("\033[H\033[2J");
						System.out.flush();

						System.out.println("\n\u001B[1;30;46mDelta Encoding (DE): \u001B[0m\n" +
								"\n\u001B[1;34mConsective Values  :- \u001B[0m \n\n" +
								"\n\u001B[1;35mInteger  :- \u001B[0m \n" +
								"\nGiven Integer  Value (DE): " + consecutive_integers_de + "\n" +

								"\n\nThe Compressed Integer  Value (DE): " + compressed_integers_de +

								"\n \nDecompressed Integer (DE) : " + decompressed_integers_de +

								"\n \n\u001B[1:35mMore Details :-\n\n" +
								"\u001B[1;31mLength Of Integer  Value (DE): "
								+ String.valueOf(consecutive_integers_de).length() + "\u001B[0m \n" +
								"\nMemory Size Of Original Data : " + memorySizeOf(consecutive_integers_de)
								+ " bits\u001B[0m\n" +

								"\nLength Of Compressed Integer  Value (DE): "
								+ String.valueOf(compressed_integers_de).length() + "\n" +
								"\nMemory Size Of Compressed Data : " + memorySizeOf(compressed_integers_de)
								+ " bits\u001B[0m\n" +
								"\n\u001B[1;32mBit Packed Size : " + bit_packed_size_integer_de
								+ " bits \u001B[0m\n" +
								"\n\u001B[1;32mInteger  Compression Percentage (DE): "
								+ (int) compressed_integers_percent_de
								+ "%\u001B[0m \n");

						break;

					case 3:
						System.out.print("\033[H\033[2J");
						System.out.flush();

						// Arraylist (String) for delta encoding :
						if (method_determiner == 1) {
							sample_string_from_user = content;
						} else {
							System.out.println(
									"Enter The String List Value For Delta Encoding [Ex: abcdef,fedcba,klmno,zyxwvutsrqpo]: ");

							sample_string_from_user = get_input_from_user.next();
						}
						ArrayList<String> consecutive_string_arraylist_de = new ArrayList<String>(
								Arrays.asList(sample_string_from_user.split(",")));
						ArrayList<String> compressed_string_arraylist_de = delta_encoding
								.delta_compress_method(consecutive_string_arraylist_de);
						ArrayList<String> decompressed_string_arraylist_de = delta_encoding
								.delta_decompress_method(compressed_string_arraylist_de);

						// Compression STRING LIST percentage calculation :
						int bit_packed_size_string_list_de = bit_packing
								.bitpack_compress_method(compressed_string_arraylist_de);

						float compressed_arraylist_string_percent_de = (memorySizeOf(consecutive_string_arraylist_de)
								- bit_packed_size_string_list_de) * 100
								/ memorySizeOf(consecutive_string_arraylist_de);
						System.out.print("\033[H\033[2J");
						System.out.flush();
						System.out.println("\n\u001B[1;30;46mDelta Encoding (DE): \u001B[0m\n" +
								"\n\u001B[1;34mConsective Values  :- \u001B[0m \n\n" +
								"\n\u001B[1;35mString List  :- \u001B[0m \n" +
								"\nGiven String List  Value (DE): " + consecutive_string_arraylist_de + "\n" +

								"\n\nThe Compressed String List  Value (DE): " + compressed_string_arraylist_de +

								"\n \nDecompressed String List (DE) : " + decompressed_string_arraylist_de +

								"\n \n\u001B[1:35mMore Details :-\n\n" +
								"\u001B[1;31mLength Of String List  Value (DE): "
								+ getTotalSize(consecutive_string_arraylist_de) + "\u001B[0m \n" +
								"\n\u001B[1;32mMemory Size Of Original Data : "
								+ memorySizeOf(consecutive_string_arraylist_de)
								+ " bits\n" +

								"\nLength Of Compressed String List  Value (DE): "
								+ getTotalSize(compressed_string_arraylist_de) + "\n" +
								"\nMemory Size Of Compressed Data : " + memorySizeOf(compressed_string_arraylist_de)
								+ " bits\n" +
								"\n\u001B[1;32mBit Packed Size : " + bit_packed_size_string_list_de
								+ " bits \u001B[0m\n" +
								"\n\u001B[1;32mString List  Compression Percentage (DE): "
								+ (int) compressed_arraylist_string_percent_de
								+ "%\u001B[0m \n");

						break;
					case 4:
						System.out.print("\033[H\033[2J");
						System.out.flush();

						if (method_determiner == 1) {
							sample_arr_de = content.split(",");
						} else {
							System.out.println(
									"Enter The Integer List Value For Delta Encoding [Ex: 123456,2468,3579]: ");
							sample_arr_de = (get_input_from_user.next()).split(",");
						}

						// Arraylist (Integer) for delta encoding :
						ArrayList<Integer> consecutive_integer_arraylist_de = new ArrayList<>();
						for (String str : sample_arr_de) {
							consecutive_integer_arraylist_de.add(Integer.parseInt(str));
						}
						ArrayList<Integer> compressed_integer_arraylist_de = delta_encoding
								.delta_compress_method_variation(consecutive_integer_arraylist_de);
						ArrayList<Integer> decompressed_integer_arraylist_de = delta_encoding
								.delta_decompress_method_variation(compressed_integer_arraylist_de);

						// Compression INTEGER LIST percentage calculation :
						int bit_packed_size_integer_list_de = bit_packing
								.bitpack_compress_method_int(compressed_integer_arraylist_de);

						float compressed_arraylist_integer_percent_de = (memorysizeOf(consecutive_integer_arraylist_de)
								- bit_packed_size_integer_list_de) * 100
								/ memorysizeOf(consecutive_integer_arraylist_de);

						System.out.print("\033[H\033[2J");
						System.out.flush();
						System.out.println("\n\u001B[1;30;46mDelta Encoding (DE): \u001B[0m\n" +
								"\n\u001B[1;34mConsective Values  :- \u001B[0m \n\n" +
								"\n\u001B[1;35mInteger List  :- \u001B[0m \n" +
								"\nGiven Integer List  Value (DE): " + consecutive_integer_arraylist_de + "\n" +

								"\n\nThe Compressed Integer List  Value (DE): " + compressed_integer_arraylist_de +

								"\n \nDecompressed Integer List (DE) : " + decompressed_integer_arraylist_de +

								"\n \n\u001B[1:35mMore Details :-\n\n" +
								"\u001B[1;31mLength Of Integer List  Value (DE): "
								+ getTotalintegerSize(consecutive_integer_arraylist_de) + "\u001B[0m \n" +
								"\nMemory Size Of Original Data : " + memorysizeOf(consecutive_integer_arraylist_de)
								+ " bits\n" +

								"\nLength Of Compressed Integer List  Value (DE): "
								+ getTotalintegerSize(compressed_integer_arraylist_de) + "\n" +
								"\nMemory Size Of Compressed Data : " + memorysizeOf(compressed_integer_arraylist_de)
								+ " bits\n" +
								"\n\u001B[1;32mBit Packed Size : " + bit_packed_size_integer_list_de
								+ " bits \u001B[0m\n" +
								"\n\u001B[1;32mInteger List  Compression Percentage (DE): "
								+ (int) compressed_arraylist_integer_percent_de
								+ "%\u001B[0m \n");

						break;

					case 5:
						System.out.print("\033[H\033[2J");
						System.out.flush();

						// Array (Integer) for delta encoding:
						if (method_determiner == 1) {
							sample_arr_de_non = content.split(",");
						} else {
							System.out.println(
									"Enter The Integer List Value For Delta Encoding [Ex: 1,2,3,4,5,6,7,8,9]: ");

							sample_arr_de_non = (get_input_from_user.next()).split(",");
						}

						int[] consecutive_integers_array_de = new int[sample_arr_de_non.length];

						for (int idx = 0; idx < sample_arr_de_non.length; idx++) {

							consecutive_integers_array_de[idx] = Integer.parseInt(sample_arr_de_non[idx]);

						}

						String compressed_integers_array_string_form_de = delta_encoding
								.delta_compress_method_variation_2(consecutive_integers_array_de);

						int[] decompressed_integers_array_de = delta_encoding
								.delta_decompress_method_variation_2(compressed_integers_array_string_form_de);

						// Compression INTEGER ARRAY percentage calculation :
						int bit_packed_size_integer_array_de = bit_packing
								.bitpack_compress_method(compressed_integers_array_string_form_de);

						float compressed_array_integer_percent_de = (memorySizeOf(consecutive_integers_array_de)
								- bit_packed_size_integer_array_de) * 100
								/ memorySizeOf(consecutive_integers_array_de);

						System.out.print("\033[H\033[2J");
						System.out.flush();
						System.out.println("\n\u001B[1;30;46mDelta Encoding (DE): \u001B[0m\n" +
								"\n\u001B[1;34mConsective Values  :- \u001B[0m \n\n" +
								"\n\u001B[1;35mInteger Array  :- \u001B[0m \n" +
								"\nGiven Integer Array  Value (DE): " + Arrays.toString(consecutive_integers_array_de)
								+ "\n" +

								"\n\nThe Compressed Integer Array  Value (DE): "
								+ compressed_integers_array_string_form_de +

								"\n \nDecompressed Integer Array(DE) : "
								+ Arrays.toString(decompressed_integers_array_de) +

								"\n \n\u001B[1:35mMore Details :-\n\n" +
								"\u001B[1;31mLength Of Integer Array  Value (DE): "
								+ consecutive_integers_array_de.length + " \n" +
								"\nMemory Size Of Original Data : " + memorySizeOf(consecutive_integers_array_de)
								+ " bits\u001B[0m\n"
								+

								"\nLength Of Compressed Integer Array  Value (DE): "
								+ compressed_integers_array_string_form_de.length() + "\n" +
								"\n\u001B[1;32mMemory Size Of Compressed Data : "
								+ memorySizeOf(compressed_integers_array_string_form_de) + " bits\u001B[0m\n" +
								"\nBit Packed Size : " + bit_packed_size_integer_array_de
								+ " bits \u001B[0m\n" +
								"\n\u001B[1;32mInteger Array  Compression Percentage (DE): "
								+ (int) compressed_array_integer_percent_de
								+ "%\u001B[0m \n");

						break;

					case 6:
						System.out.print("\033[H\033[2J");
						System.out.flush();

						if (method_determiner == 1) {
							sample_arr_de_non_int = content.split(",");
						} else {
							System.out.println(
									"Enter The Integer Array For Delta Encoding [Ex: 2001, 2000, 1995, 2003, 2005]: ");
							sample_arr_de_non_int = (get_input_from_user.next()).split(",");
						}

						// Integer cources for non consecutive data in Delta Encoding:

						int[] non_consecutive_integers_array_de = new int[sample_arr_de_non_int.length];

						for (int idx = 0; idx < sample_arr_de_non_int.length; idx++) {

							non_consecutive_integers_array_de[idx] = Integer.parseInt(sample_arr_de_non_int[idx]);

						}
						int[] non_consecutive_compressed_integers_array_de = delta_encoding
								.delta_compress_method_non_consecutive(non_consecutive_integers_array_de);

						int[] non_consecutive_decompressed_integers_array_de = delta_encoding
								.delta_decompress_method_non_consecutive(non_consecutive_compressed_integers_array_de);

						System.out.print("\033[H\033[2J");
						System.out.flush();
						System.out.println("\n\u001B[1;30;46mDelta Encoding (DE): \u001B[0m\n" +
								"\n\u001B[1;34mNon - Consective Values  :- \u001B[0m \n\n" +
								"\n\u001B[1;35mInteger Array  :- \u001B[0m \n" +
								"\nGiven Integer Array  Value (DE): "
								+ Arrays.toString(non_consecutive_integers_array_de) + "\n" +

								"\n\nThe Compressed Integer Array  Value (DE): "
								+ Arrays.toString(non_consecutive_compressed_integers_array_de) +

								"\n \nDecompressed Integer Array(DE) : "
								+ Arrays.toString(non_consecutive_decompressed_integers_array_de) +

								"\n \n\u001B[1:35mMore Details :-\n\n" +
								"\u001B[1;31mLength Of Integer Array  Value (DE): "
								+ non_consecutive_integers_array_de.length + "\u001B[0m \n" +
								"\n\u001B[1;31Memory Size Of Original Data : "
								+ memorySizeOf(non_consecutive_integers_array_de)
								+ " bits\u001B[0m\n" +

								"\n\u001B[1;32mLength Of Compressed Integer Array  Value (DE): "
								+ non_consecutive_compressed_integers_array_de.length + "\n" +
								"\nMemory Size Of Compressed Data : "
								+ memorySizeOf(non_consecutive_compressed_integers_array_de) + " bits\u001B[0m\n"

						);
						break;

					case 7:
						System.out.print("\033[H\033[2J");
						System.out.flush();

						if (method_determiner == 1) {
							non_consecutive_string_de = content.toCharArray();
						} else {
							System.out.println(
									"Enter The Character Array For Delta Encoding [Ex: 'a', 'c', 'v', 'e', 'g']: ");

							non_consecutive_string_de = get_input_from_user.nextLine().toCharArray();
						}

						ArrayList<Byte> compressed_non_consecutivr_string_de = delta_encoding
								.delta_compress_method_non_consecutive(non_consecutive_string_de);
						char[] decompressed_non_consecutive_string_de = delta_encoding
								.delta_decompress_method_non_consecutive(compressed_non_consecutivr_string_de);

						System.out.print("\033[H\033[2J");
						System.out.flush();
						System.out.println("\n\u001B[1;30;46mDelta Encoding (DE): \u001B[0m\n" +
								"\n\u001B[1;34mNon - Consective Values  :- \u001B[0m \n\n" +
								"\n\u001B[1;35mCharacter Array :- \u001B[0m \n" +
								"\nGiven Character Array Value (DE): " + Arrays.toString(non_consecutive_string_de)
								+ "\n" +

								"\n\nThe Compressed Character Array Value (DE): " + compressed_non_consecutivr_string_de
								+

								"\n \nDecompressed Character Array(DE) : "
								+ Arrays.toString(decompressed_non_consecutive_string_de) +

								"\n \n\u001B[1;35mMore Details :-\n\n" +
								"\u001B[1;31mLength Of Character Array  Value (DE): " + non_consecutive_string_de.length
								+ "\u001B[0m \n" +
								"\nMemory Size Of Original Data : "
								+ memorySizeOf(String.valueOf(non_consecutive_string_de)) + " bits\n" +

								"\nLength Of Compressed Character Array  Value (DE): "
								+ compressed_non_consecutivr_string_de.size() + "\n" +
								"\nMemory Size Of Compressed Data : "
								+ memorySizeof(compressed_non_consecutivr_string_de) + " bits\n"

						);
						break;

				}
				break;
			case "  BIT PACKING - BIT":
				switch (type) {

					case 1:
						System.out.print("\033[H\033[2J");
						System.out.flush();

						// String sources for Bit Packing :
						if (method_determiner == 1) {
							source_string_for_bitpack = content;
						} else {
							System.out.println("Enter The String For Bit Packing [Ex: abcdefghi]: ");

							source_string_for_bitpack = get_input_from_user.next();
						}

						// Compression STRING percentage calculation :
						int bit_packed_size_string_bp = bit_packing.bitpack_compress_method(source_string_for_bitpack);

						float compressed_string_percent_bitpack = (memorySizeOf(source_string_for_bitpack)
								- bit_packed_size_string_bp) * 100
								/ memorySizeOf(source_string_for_bitpack);

						System.out.print("\033[H\033[2J");
						System.out.flush();
						System.out.println("\n\u001B[1;30;46m Bit Paking (BIT): \u001B[0m\n" +
								"\n\u001B[1;35mString :- \u001B[0m \n" +
								"\nGiven String Value (BIT): " + source_string_for_bitpack +
								"\n\u001B[1;31m\nMemory Size : " + memorySizeOf(source_string_for_bitpack)
								+ " bits \u001B[0m \n" +

								"\n\u001B[1;32mBit Packed Size : " + bit_packed_size_string_bp
								+ " bits \u001B[0m\n" +
								"\n \n\u001B[1;32mString Compression Percentage (BIT): "
								+ (int) compressed_string_percent_bitpack + "% \u001B[0m \n"

						);
						break;

					case 2:

						System.out.print("\033[H\033[2J");
						System.out.flush();

						// Integer sources for Bit Packing :
						if (method_determiner == 1) {
							source_integer_for_bitpack = Integer.parseInt(content);
						} else {
							System.out.println("Enter The Integer For Bit Packing [Ex: 2356854]: ");

							source_integer_for_bitpack = get_input_from_user.nextInt();
						}

						// Compression INTEGER percentage calculation :
						int bit_packed_size_integer_bp = bit_packing
								.bitpack_compress_method(source_integer_for_bitpack);

						float compressed_integer_percent_bitpack = (memorySizeOf(source_integer_for_bitpack)
								- bit_packed_size_integer_bp) * 100
								/ memorySizeOf(source_integer_for_bitpack);
						System.out.print("\033[H\033[2J");
						System.out.flush();
						System.out.println("\n\u001B[1;30;46m Bit Paking (BIT): \u001B[0m\n" +
								"\n\u001B[1;35mString :- \u001B[0m \n" +
								"\nGiven Integer Value (BIT): " + source_integer_for_bitpack +

								"\n\u001B[1;31m\nMemory Size : " + memorySizeOf(source_integer_for_bitpack)
								+ " bits \u001B[0m  \n" +

								"\n\u001B[1;32mBit Packed Size : "
								+ bit_packing.bitpack_compress_method(source_integer_for_bitpack)
								+ " bits \u001B[0m\n" +
								"\n \n\u001B[1;32mInteger Compression Percentage (BIT): "
								+ (int) compressed_integer_percent_bitpack + "% \u001B[0m \n"

						);
						break;
				}
				break;
		}

		for (int i = 0; i < 25; i++) {

			System.out.println();
		}

		System.out.println(
				"\u001B[1;32m Wanna do again ? Press 1 | Enter 2 to return back | Enter 10 to exit \u001B[0m :\n");
		Scanner back = new Scanner(System.in);
		int return_back = back.nextInt();
		if (return_back == 1) {
			seek_input_and_process(scheme_name, type, method_determiner);
		} else if (return_back == 10) {

			for (int i = 0; i < 10; i++) {

				System.out.println();
			}
			for (int d = 0; d < 50; d++) {

				System.out.print("");
				if (d == 49) {
					System.out.print("\u001B[1;32mComplex ?  - Simply Compress, Relax And Enjoy !!! \u001B[0m \n");
				}
			}

			System.exit(0);
		} else if (return_back == 2) {
			QuickShrink for_back = new QuickShrink();

			for_back.file_approval_process(true);
		}

	}

	String readFileToString(String fileName) throws IOException {
		Path filePath = Paths.get(fileName);
		byte[] fileBytes = Files.readAllBytes(filePath);
		return new String(fileBytes, StandardCharsets.UTF_8);
	}

	// Methods to find the length and size of the data

	int getTotalSize(ArrayList<String> arrayList_string) {
		int totalSize = 0;
		for (String element : arrayList_string) {
			totalSize += element.length();
		}
		return totalSize;
	}

	int getTotalintegerSize(ArrayList<Integer> arrayList_integer) {
		int totalSize = 0;
		for (int element : arrayList_integer) {
			totalSize += String.valueOf(element).length();
		}
		return totalSize;
	}

	int getTotalintegerSize(int[] arrayList_integer) {
		int totalSize = 0;
		for (int element : arrayList_integer) {
			totalSize += String.valueOf(element).length();
		}
		return totalSize;
	}

	int memorySizeOf(String s) {

		int size = 0;
		for (char c : s.toCharArray()) {
			size += 16;
		}
		return size;

	}

	int memorySizeOf(int integer) {
		int size = 0;
		int temp_integer = integer;
		int n_of_values = 0;
		int splitted_number = 0;
		while (temp_integer != 0) {
			splitted_number = temp_integer % 10;
			temp_integer = (temp_integer - splitted_number) / 10;
			n_of_values++;
		}
		return n_of_values * 32;
	}

	int memorySizeOf(int[] intArray) {
		return intArray.length * 32;
	}

	int memorySizeof(ArrayList<Byte> byteArray) {
		return byteArray.size() * 8;

	}

	int memorySizeOf(ArrayList<String> strArray) {
		int totalSize = 0;
		for (String element : strArray) {
			totalSize += element.length() * 16;
		}
		return totalSize;
	}

	int memorysizeOf(ArrayList<Integer> arrayList_integer) {
		int totalSize = 0;
		for (int element : arrayList_integer) {
			totalSize += String.valueOf(element).length() * 32;
		}
		return totalSize;
	}

}

class QuickShrink {
	static int terminalWidth = 0;
	static int centerPosition = 0;
	static int leftPadding = 0;
	static String intro_text = "\u001B[1;32m" + "\n" + //
			"   ___        _     ___        _____ __         _       __      \n" + //
			" / _ \\ _   _(_) ___| | __    / ___|| |__  _ __(_)_ __ | | __ \n" + //
			"| | | | | | | |/ __| |/ /    \\___ \\| '_ \\| '__| | '_ \\| |/ / \n" + //
			"| |_| | |_| | | (__|   <      ___) | | | | |  | | | | |   <  \n" + //
			"   \\__\\_\\\\__,_|_|\\___|_|\\_\\    |____/|_| |_|_|  |_|_| |_|_|\\_\\\u001B[0m";
	static String[] lines = intro_text.split("\n");

	public static void main(String[] args) throws Exception {

		initialize_the_program();
	}

	static void initialize_the_program() throws Exception {

		Scanner process_determiner = new Scanner(System.in);
		int operation_type = 0;

		terminalWidth = 210;

		centerPosition = (50 - lines.length) / 2;

		for (int i = 0; i < centerPosition; i++) {
			System.out.println();
		}

		leftPadding = 0;
		for (String line : lines) {
			leftPadding = (terminalWidth - line.length()) / 2;
			for (int i = 0; i < leftPadding; i++) {
				System.out.print(" ");
			}
			System.out.println(line);

		}
		System.out.println(
				"\u001B[1;32m                                                                                                                                    V1.0\u001B[0m");
		centerPosition = (15 - lines.length) / 2;

		for (int i = 0; i < centerPosition; i++) {
			System.out.println();

		}
		System.out.println(
				"\u001B[1;32m                                                                                       ««««  SELECT THE OPERATIONS  »»»» \u001B[0m\n");
		System.out.println(
				"\u001B[1;32m                                                                                            [1]  Start Compression\u001B[0m\n");
		System.out.println(
				"\u001B[1;32m                                                                                              [2]  Instructions\u001B[0m\n");
		System.out.println(
				"\n\n\u001B[1;32m                                                                                                  [10]  Exit\u001B[0m");
		centerPosition = (35 - lines.length) / 2;

		for (int i = 0; i < centerPosition; i++) {
			System.out.println();

		}
		System.out.println("\u001B[1;32m   Which operation are you going to start [ 1 | 2 | 10 ] ?\u001B[0m");
		operation_type = process_determiner.nextInt();

		switch (operation_type) {

			case 1:
				file_approval_process(true);
				break;
			case 2:
				instructions_page();
				break;
			default:

				break;

		}
	}

	static void file_approval_process(String s, int type) throws Exception {

		Scanner process_determiner = new Scanner(System.in);
		int operation_type = 0;

		centerPosition = (50 - lines.length) / 2;

		for (int i = 0; i < centerPosition; i++) {
			System.out.println();

		}

		leftPadding = 0;
		for (String line : lines) {
			leftPadding = (terminalWidth - line.length()) / 2;
			for (int i = 0; i < leftPadding; i++) {
				System.out.print(" ");
			}
			System.out.println(line);

		}
		System.out.println(
				"\u001B[1;32m                                                                                                                                    V1.0\u001B[0m");
		centerPosition = (15 - lines.length) / 2;

		for (int i = 0; i < centerPosition; i++) {
			System.out.println();
		}
		System.out.println(
				"\u001B[1;32m                                                                                       ««««  SELECT THE OPERATIONS  »»»» \u001B[0m\n");
		System.out.println(
				"\u001B[1;32m                                                                                           [1] Upload a .txt file\u001B[0m\n");
		System.out.println(
				"\u001B[1;32m                                                                                          [2] Manual Input Method\u001B[0m\n");
		System.out.println(
				"\n\n\u001B[1;32m                                                                                             [10]  Main Menu\u001B[0m");
		centerPosition = (40 - lines.length) / 2;

		for (int i = 0; i < centerPosition; i++) {
			System.out.println();

		}
		System.out.println("\u001B[1;32m   Which operation are you going to start [ 1 | 2 | 10 ] ?\u001B[0m");
		operation_type = process_determiner.nextInt();
		Input_Seeker get_input_and_send = new Input_Seeker();
		switch (operation_type) {

			case 1:
				get_input_and_send.seek_input_and_process(s, type, 1);
				break;
			case 2:
				get_input_and_send.seek_input_and_process(s, type, 2);
				break;
			default:
				file_approval_process(true);
				break;

		}
	}

	static void file_approval_process(boolean b) throws Exception {
		Scanner process_determiner = new Scanner(System.in);
		int operation_type = 0;

		centerPosition = (50 - lines.length) / 2;

		for (int i = 0; i < centerPosition; i++) {
			System.out.println();

		}

		leftPadding = 0;
		for (String line : lines) {
			leftPadding = (terminalWidth - line.length()) / 2;
			for (int i = 0; i < leftPadding; i++) {
				System.out.print(" ");
			}
			System.out.println(line);

		}
		System.out.println(
				"\u001B[1;32m                                                                                                                                    V1.0\u001B[0m");
		centerPosition = (15 - lines.length) / 2;

		for (int i = 0; i < centerPosition; i++) {
			System.out.println();
		}
		System.out.println(
				"\u001B[1;32m                                                                                 ««««  SELECT THE COMPRESSION SCHEME YOU WANT  »»»» \u001B[0m\n");
		System.out.println(
				"\n\u001B[1;32m                                                                  [1] Run Length Encoding    [2] Delta Encoding    [3] Bit Packing    [10] Main Menu \u001B[0m\n");
		centerPosition = (40 - lines.length) / 2;

		for (int i = 0; i < centerPosition; i++) {
			System.out.println();

		}
		System.out.println(
				"\u001B[1;32m   Which compression scheme do you want to perform [ 1 | 2 | 3 | 10] ?\u001B[0m");
		operation_type = process_determiner.nextInt();
		switch (operation_type) {

			case 1:
				print_the_data_types("RUN LENGTH ENCODING - RLE");
				break;
			case 2:
				print_the_data_types("   DELTA ENCODING - DE");
				break;
			case 3:
				print_the_data_types("  BIT PACKING - BIT");
				break;
			default:
				initialize_the_program();
				break;

		}

	}

	static void instructions_page() throws Exception {

		Scanner process_determiner = new Scanner(System.in);
		int operation_type = 0;

		centerPosition = (50 - lines.length) / 2;

		for (int i = 0; i < centerPosition; i++) {
			System.out.println();

		}

		leftPadding = 0;
		for (String line : lines) {
			leftPadding = (terminalWidth - line.length()) / 2;
			for (int i = 0; i < leftPadding; i++) {
				System.out.print(" ");
			}
			System.out.println(line);

		}
		System.out.println(
				"\u001B[1;32m                                                                                                                                    V1.0\u001B[0m");
		centerPosition = (15 - lines.length) / 2;

		for (int i = 0; i < centerPosition; i++) {
			System.out.println();
		}
		System.out.println(
				"\u001B[1;32m                                                                                 ««««  SELECT THE COMPRESSION SCHEME YOU WANT  »»»» \u001B[0m\n");
		System.out.println(
				"\n\u001B[1;32m                                                                  [1] Run Length Encoding    [2] Delta Encoding    [3] Bit Packing    [10] Main Menu \u001B[0m\n");
		centerPosition = (40 - lines.length) / 2;

		for (int i = 0; i < centerPosition; i++) {
			System.out.println();

		}
		System.out.println("\u001B[1;32m   Which compression scheme do you want to know [ 1 | 2 | 3 | 10] ?\u001B[0m");
		operation_type = process_determiner.nextInt();
		switch (operation_type) {

			case 1:
				instruction_print("RUN LENGTH ENCODING - RLE");
				break;
			case 2:
				instruction_print("DELTA ENCODING - DE");
				break;
			case 3:
				instruction_print("      BIT PACKING - BIT");
				break;
			default:
				initialize_the_program();
				break;

		}
	}

	static void instruction_print(String s) throws Exception {
		String description = "";
		String table = "";
		String best_case = "";
		String worst_case = "";

		Scanner process_determiner = new Scanner(System.in);
		int operation_type = 0;

		centerPosition = (50 - lines.length) / 2;

		for (int i = 0; i < centerPosition; i++) {
			System.out.println();

		}

		leftPadding = 0;
		for (String line : lines) {
			leftPadding = (terminalWidth - line.length()) / 2;
			for (int i = 0; i < leftPadding; i++) {
				System.out.print(" ");
			}
			System.out.println(line);

		}

		switch (s) {

			case "RUN LENGTH ENCODING - RLE":
				description = "\u001B[1;32mRLE is a compression method that replaces consecutive repeated elements in a sequence with a single element and a count of occurrences.\u001B[0m";
				best_case = "AAAABBBCCDAA → A4B3C2DA2";
				worst_case = "ABCDE → ABCDE";
				table = "==============================================================================\n" +
						"|            Best Case               ||             Worst Case                |\n" +
						"|----------------------------------------------------------------------------|\n" +
						"|                                                                             |\n" +
						"|      " + best_case + "      ||            " + worst_case + "              |\n" +
						"|                                                                             |\n" +
						"==============================================================================";
				valuePrint(s, description, table, best_case, worst_case);
				break;

			case "DELTA ENCODING - DE":
				description = "\u001B[1;32mDelta Encoding compresses data by capturing changes between consecutive elements, particularly effective for ordered or repetitive sequences.\u001B[0m";
				best_case = "ABCDEFGHIJKLMNOPQRSTUV → AV-1";
				worst_case = "ANCH → 97, 110, 99, 104";
				table = "==============================================================================\n" +
						"|            Best Case               ||             Worst Case                |\n" +
						"|-----------------------------------------------------------------------------|\n" +
						"|                                                                             |\n" +
						"|    " + best_case + "   ||       " + worst_case + "         |\n" +
						"|                                                                             |\n" +
						"==============================================================================";
				valuePrint(s, description, table, best_case, worst_case);
				break;

			case "      BIT PACKING - BIT":
				description = "\u001B[1;32m              Bitpacking compresses multiple values into a single word, using fewer bits than the original representation.\u001B[0m";
				best_case = "ANY DATA → MAXIMUM COMPRESSION";
				worst_case = "ALREADY COMPRESSED → REMAINS SAME";
				table = "==============================================================================\n" +
						"|            Best Case               ||             Worst Case                |\n" +
						"|-----------------------------------------------------------------------------|\n" +
						"|                                                                             |\n" +
						"|    " + best_case + "  ||   " + worst_case + "   |\n" +
						"|                                                                             |\n" +
						"==============================================================================";
				valuePrint(s, description, table, best_case, worst_case);
				break;

		}

	}

	static void valuePrint(String name, String Description, String Table, String bestCase, String worstCase)
			throws Exception {
		String intro_text = "\u001B[1;32m" + "\n" + //
				"   ___        _     ___        _____ __         _       __      \n" + //
				" / _ \\ _   _(_) ___| | __    / ___|| |__  _ __(_)_ __ | | __ \n" + //
				"| | | | | | | |/ __| |/ /    \\___ \\| '_ \\| '__| | '_ \\| |/ / \n" + //
				"| |_| | |_| | | (__|   <      ___) | | | | |  | | | | |   <  \n" + //
				"   \\__\\_\\\\__,_|_|\\___|_|\\_\\    |____/|_| |_|_|  |_|_| |_|_|\\_\\\u001B[0m";

		lines = intro_text.split("\n");
		centerPosition = (3 - lines.length) / 2;

		for (int i = 0; i < centerPosition; i++) {
			System.out.println();
		}
		leftPadding = 0;
		for (String line : lines) {
			leftPadding = (terminalWidth - line.length()) / 2;
			for (int i = 0; i < leftPadding; i++) {
				System.out.print(" ");
			}
			System.out.println();
		}

		leftPadding = 0;
		for (String line : lines) {
			leftPadding = 30 / 2;
			for (int i = 0; i < leftPadding; i++) {
				System.out.print(" ");
			}
		}

		System.out.print("\u001B[1;32m" + name + "\u001B[1;32m" + "\n \n \n");
		for (String l : lines) {
			leftPadding = 12 / 2;
			for (int i = 0; i < leftPadding; i++) {
				System.out.print(" ");
			}
		}
		for (char c : Description.toCharArray()) {
			System.out.print(c);
			Thread.sleep(50);
		}
		centerPosition = (15 - lines.length) / 2;

		for (int i = 0; i < centerPosition; i++) {
			System.out.println();

		}

		String[] table_list_for_println = Table.split("\n");
		for (String l : table_list_for_println) {
			leftPadding = 130 / 2;
			for (int i = 0; i < leftPadding; i++) {
				System.out.print(" ");
			}
			System.out.print(l + "\n");
		}

		for (String l : lines) {
			leftPadding = 145 / 2;
			for (int i = 0; i < leftPadding; i++) {
				System.out.print(" ");
			}
		}
		System.out.print(
				"\n\u001B[1;32m                                                                                                   [10] Main Menu \u001B[1;32m\n");

		centerPosition = (30 - lines.length) / 2;

		for (int i = 0; i < centerPosition; i++) {
			System.out.println();

		}

		System.out.println("  Enter 10 to Return : ");
		Scanner out = new Scanner(System.in);

		int Out = out.nextInt();
		if (Out == 10) {
			initialize_the_program();
		}

	}

	static void print_the_data_types(String s) throws Exception {

		int Out = 0;
		int t = 0;
		Scanner process_determiner = new Scanner(System.in);
		Scanner out = new Scanner(System.in);
		int operation_type = 0;

		centerPosition = (50 - lines.length) / 2;

		for (int i = 0; i < centerPosition; i++) {
			System.out.println();

		}
		leftPadding = 0;
		for (String line : lines) {
			leftPadding = (terminalWidth - line.length()) / 2;
			for (int i = 0; i < leftPadding; i++) {
				System.out.print(" ");
			}
			System.out.println(line);
		}

		for (String l : lines) {
			leftPadding = 172 / 2;
			for (int i = 0; i < leftPadding; i++) {
				System.out.print(" ");
			}
		}
		System.out.print("\u001B[1;32m" + s + "\u001B[1;32m" + "\n \n \n");

		centerPosition = (5 - lines.length) / 2;

		for (int i = 0; i < centerPosition; i++) {
			System.out.println();

		}

		System.out.println(
				"\u001B[1;32m                                                                                     ««««  SELECT THE DATA TYPE YOU WANT  »»»» \u001B[0m\n");

		centerPosition = (5 - lines.length) / 2;

		for (int i = 0; i < centerPosition; i++) {
			System.out.println();

		}

		switch (s) {

			case "RUN LENGTH ENCODING - RLE":
				for (String l : lines) {
					leftPadding = 28 / 2;
					for (int i = 0; i < leftPadding; i++) {
						System.out.print(" ");
					}
				}

				System.out.print("\u001B[1;32m [1] String     [2] Integer     [3] String List  \u001B[0m" + "\n \n \n");
				centerPosition = (22 - lines.length) / 2;

				for (int i = 0; i < centerPosition; i++) {
					System.out.println();

				}

				for (String l : lines) {
					leftPadding = 33 / 2;
					for (int i = 0; i < leftPadding; i++) {
						System.out.print(" ");
					}
				}

				System.out.print("\u001B[1;32m [10] Main Menu ");
				centerPosition = (26 - lines.length) / 2;

				for (int i = 0; i < centerPosition; i++) {
					System.out.println();

				}
				System.out.println("\n  Enter 10 to Return :- ");
				System.out.println("\n \n  Which data type do you want to use ? Press relevant keys :  ");

				t = out.nextInt();
				if (t == 10) {
					initialize_the_program();
				}

				if ((t > 4) && (t != 10)) {
					System.out.println("\n  Entered value not in range ");
					System.out.println("\n \n  Which data type do you want to use ? Press relevant keys :  ");

					t = out.nextInt();

				} else {
					file_approval_process(s, t);
				}

				break;

			case "   DELTA ENCODING - DE":
				for (String l : lines) {
					leftPadding = 15 / 2;
					for (int i = 0; i < leftPadding; i++) {
						System.out.print(" ");
					}
				}

				System.out.print(
						"\u001B[1;32m [1] String     [2] Integer     [3] String List     [4] Integer List    [5] Integer Array \u001B[0m  \u001B[2;33m [6] Integer Array    [7] Character Array \u001B[0m"
								+ "\n \n \n");
				centerPosition = (22 - lines.length) / 2;

				for (int i = 0; i < centerPosition; i++) {
					System.out.println();

				}

				for (String l : lines) {
					leftPadding = 33 / 2;
					for (int i = 0; i < leftPadding; i++) {
						System.out.print(" ");
					}
				}

				System.out.print("\u001B[1;32m [10] Main Menu ");
				centerPosition = (26 - lines.length) / 2;

				for (int i = 0; i < centerPosition; i++) {
					System.out.println();

				}
				System.out.println("\n  Enter 10 to Return :- ");
				System.out.println("\n \n  Which data type do you want to use ? Press relevant keys :  ");

				t = out.nextInt();
				if (t == 10) {
					initialize_the_program();
				}

				if ((t > 8) && (t != 10)) {
					System.out.println("\n  Entered value not in range ");
					System.out.println("\n \n  Which data type do you want to use ? Press relevant keys :  ");

					t = out.nextInt();

				} else {
					file_approval_process(s, t);
				}

				break;

			case "  BIT PACKING - BIT":
				for (String l : lines) {
					leftPadding = 31 / 2;
					for (int i = 0; i < leftPadding; i++) {
						System.out.print(" ");
					}
				}
				System.out.print("\u001B[1;32m [1] String     [2] Integer  \u001B[0m" + "\n \n \n");
				centerPosition = (22 - lines.length) / 2;

				for (int i = 0; i < centerPosition; i++) {
					System.out.println();

				}

				for (String l : lines) {
					leftPadding = 33 / 2;
					for (int i = 0; i < leftPadding; i++) {
						System.out.print(" ");
					}
				}

				System.out.print("\u001B[1;32m [10] Main Menu ");
				centerPosition = (26 - lines.length) / 2;

				for (int i = 0; i < centerPosition; i++) {
					System.out.println();

				}
				System.out.println("\n  Enter 10 to Return :- ");
				System.out.println("\n \n  Which data type do you want to use ? Press relevant keys :  ");

				t = out.nextInt();
				if (t == 10) {
					initialize_the_program();
				}

				if ((t > 3) && (t != 10)) {
					System.out.println("\n  Entered value not in range ");
					System.out.println("\n \n  Which data type do you want to use ? Press relevant keys :  ");

					t = out.nextInt();

				} else {
					file_approval_process(s, t);
				}
				break;
		}
	}
}