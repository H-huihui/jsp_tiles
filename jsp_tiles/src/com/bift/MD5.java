package com.bift;

//MD5 message-digest algorithm MD5.java

/**

* <DT><B>名称：</B><DD> MD5 加密

* <DT><B>概要：</B><DD> 将指定字符串经MD5加密处理

* <DT><B>说明：</B><DD> 通过MD5加密算法加密指定字符串

* @声明：b2b专用，未经允许，不得盗用

*/

public class MD5

{

  private long[] m_buf    = new long[4];  /* state (ABCD) */

  private long[] m_bits   = new long[2];  /* number of bits, modulo 2^64 (lsb first) */

  private byte[] m_in     = new byte[64]; /* input buffer */



  private char[] HEX = {  '0', '1', '2', '3', '4', '5', '6', '7',

                          '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'  };

  /** 说明 获取MD5加密后字符

   * @param  src (需要加密的内容)

   * @return src 返回经过加密处理后的字符串

   */

  public String toDigest(String src)

  {

      byte[] digest = toDigest(src.getBytes());

      StringBuffer sb = new StringBuffer();

      for (int i = 0; i < digest.length; i++)

      {

          sb.append(HEX[(int)(digest[i] & 0x0ff) / 16]);

          sb.append(HEX[(int)(digest[i] & 0x0ff) % 16]);

      }

      return sb.toString();

  }



  /** 说明 获取MD5加密后字符

   * @param  src (需要加密的内容)

   * @return src 返回经过加密处理后的字符串

   */

  public byte[] toDigest(byte[] src)

  {

      byte[] digest = new byte[16];

      int len = src.length;



      MD5Init();

      MD5Update(src, len);

      MD5Final(digest);



      return(digest);

  }



  public void memset(byte[] des, int des_offset, byte dat, int len)

  {

      int i;

      for (i = 0; i < len; i++)

          des[des_offset + i] = dat;

  }

  private void memset(long[] des, int des_offset, long dat, int len)

  {

      int i;

      for (i = 0; i < len; i++)

          des[des_offset + i] = dat;

  }



  private void memcpy(byte[] des, int des_offset, byte[] src, int src_offset, int len)

  {

      int i;

      for (i = 0; i < len; i++)

          des[des_offset + i] = src[src_offset + i];

  }



  private long bp2long(byte[] src, int offset_lng)

  {

      long    ret = 0;

      ret = ((long)((src[(offset_lng * 4) + 0]      ) & 0x0000000ffL))

            | ((long)((src[(offset_lng * 4) + 1] << 8 ) & 0x00000ff00L))

            | ((long)((src[(offset_lng * 4) + 2] << 16) & 0x000ff0000L))

            | ((long)((src[(offset_lng * 4) + 3] << 24) & 0x0ff000000L));

      return (ret & 0x0ffffffffL);

  }



  /*

   * Start MD5 accumulation.  Set bit count to 0 and buffer to mysterious

   * initialization constants.

   */

  private void MD5Init()

  {

      m_buf[0] = 0x067452301L;

      m_buf[1] = 0x0efcdab89L;

      m_buf[2] = 0x098badcfeL;

      m_buf[3] = 0x010325476L;



      m_bits[0] = 0;

      m_bits[1] = 0;

  }



  /*

   * Update context to reflect the concatenation of another buffer full

   * of bytes.

   */

  public void MD5Update(byte[] buf, int len)

  {

      long    t;



      /* Update bitcount */



      t = m_bits[0];

      m_bits[0] = t + (len << 3);

      if ((m_bits[0]) < t)

      {

          m_bits[1]++;    /* Carry from low to high */

      }

      m_bits[1] = m_bits[1] + (len >> 29);



      t = (t >> 3) & 0x3fL;   /* Bytes already in shsInfo->data */



      /* Handle any leading odd-sized chunks */



      if (t != 0L)

      {

          long    p = t;



          t = (64 - t) & 0x0ffffffffL;

          if (len < t)

          {

              memcpy(m_in, (int)t, buf, 0, len);

              return;

          }

          memcpy(m_in, (int)t, buf, 0, (int)t);

          MD5Transform();



          len -= t;

      }

      /* Process data in 64-byte chunks */



      while (len >= 64)

      {

          memcpy(m_in, 0, buf, (int)t, 64);

          MD5Transform();

          t   += 64;

          len -= 64;

      }



      /* Handle any remaining bytes of data. */



      memcpy(m_in, 0, buf, (int)t, len);

  }



  /*

   * Final wrapup - pad to 64-byte boundary with the bit pattern

   * 1 0* (64-bit count of bits processed, MSB-first)

   */

  private void MD5Final(byte[] digest)

  {

      long    count;

      long    p;



      /* Compute number of bytes mod 64 */

      count = (m_bits[0] >> 3) & 0x3fL;



      /* Set the first char of padding to 0x80.  This is safe since there is

         always at least one byte free */

      m_in[(int)count] = (byte)0x080;

      p = count + 1;



      /* Bytes of padding needed to make 64 bytes */

      count = 64 - 1 - count;



      /* Pad out to 56 mod 64 */

      if (count < 8)

      {

          /* Two lots of padding:  Pad the first block to 64 bytes */

          memset(m_in, (int)p, (byte)0, (int)count);

          MD5Transform();



          /* Now fill the next block with 56 bytes */

          memset(m_in, 0, (byte)0, 56);

      }

      else

      {

          /* Pad block to 56 bytes */

          memset(m_in, (int)p, (byte)0, (int)(count - 8));

      }



      /* Append length in bits and transform */

      m_in[(14 * 4) + 0] = (byte)(m_bits[0] & 0x0ff);

      m_in[(14 * 4) + 1] = (byte)((m_bits[0] >>  8) & 0x0ff);

      m_in[(14 * 4) + 2] = (byte)((m_bits[0] >> 16) & 0x0ff);

      m_in[(14 * 4) + 3] = (byte)((m_bits[0] >> 24) & 0x0ff);

      m_in[(15 * 4) + 0] = (byte)(m_bits[1] & 0x0ff);

      m_in[(15 * 4) + 1] = (byte)((m_bits[1] >>  8) & 0x0ff);

      m_in[(15 * 4) + 2] = (byte)((m_bits[1] >> 16) & 0x0ff);

      m_in[(15 * 4) + 3] = (byte)((m_bits[1] >> 24) & 0x0ff);



      MD5Transform();



      for(int i = 0; i < 4; i++)

      {

          digest[(i * 4) + 0] = (byte)(m_buf[i]         & 0x0ff);

          digest[(i * 4) + 1] = (byte)((m_buf[i] >>  8) & 0x0ff);

          digest[(i * 4) + 2] = (byte)((m_buf[i] >> 16) & 0x0ff);

          digest[(i * 4) + 3] = (byte)((m_buf[i] >> 24) & 0x0ff);

      }



      MD5Init();

  }



  /* The four core functions - F1 is optimized somewhat */



  /* #define F1(x, y, z) (x & y | ~x & z) */

  private long    F1(long x, long y, long z)

  {

      return((z ^ (x & (y ^ z)))  & 0x0ffffffffL);

  }

  private long    F2(long x, long y, long z)

  {

      return(F1(z, x, y));

  }

  private long    F3(long x, long y, long z)

  {

      return((x ^ y ^ z)          & 0x0ffffffffL);

  }

  private long    F4(long x, long y, long z)

  {

      return((y ^ (x | ~z))       & 0x0ffffffffL);

  }



  /* This is the central step in the MD5 algorithm. */

  private long MD5STEP(long w, long f, long x, long y, long z, long data, long s)

  {

      w = (w + f + data) & 0x0ffffffffL;

      w = ((w << s) | (w >> 32 - s)) & 0x0ffffffffL;

      w = (w + x) & 0x0ffffffffL;

      return(w);

  }



  /*

   * The core of the MD5 algorithm, this alters an existing MD5 hash to

   * reflect the addition of 16 longwords of new data.  MD5Update blocks

   * the data and converts bytes into longwords for this routine.

   */

  private void MD5Transform()

  {

      long    a, b, c, d;



      a = m_buf[0];

      b = m_buf[1];

      c = m_buf[2];

      d = m_buf[3];



      a = MD5STEP(a, F1(b, c, d), b, c, d, bp2long(m_in,  0) + 0x0d76aa478L, 7);

      d = MD5STEP(d, F1(a, b, c), a, b, c, bp2long(m_in,  1) + 0x0e8c7b756L, 12);

      c = MD5STEP(c, F1(d, a, b), d, a, b, bp2long(m_in,  2) + 0x0242070dbL, 17);

      b = MD5STEP(b, F1(c, d, a), c, d, a, bp2long(m_in,  3) + 0x0c1bdceeeL, 22);

      a = MD5STEP(a, F1(b, c, d), b, c, d, bp2long(m_in,  4) + 0x0f57c0fafL, 7);

      d = MD5STEP(d, F1(a, b, c), a, b, c, bp2long(m_in,  5) + 0x04787c62aL, 12);

      c = MD5STEP(c, F1(d, a, b), d, a, b, bp2long(m_in,  6) + 0x0a8304613L, 17);

      b = MD5STEP(b, F1(c, d, a), c, d, a, bp2long(m_in,  7) + 0x0fd469501L, 22);

      a = MD5STEP(a, F1(b, c, d), b, c, d, bp2long(m_in,  8) + 0x0698098d8L, 7);

      d = MD5STEP(d, F1(a, b, c), a, b, c, bp2long(m_in,  9) + 0x08b44f7afL, 12);

      c = MD5STEP(c, F1(d, a, b), d, a, b, bp2long(m_in, 10) + 0x0ffff5bb1L, 17);

      b = MD5STEP(b, F1(c, d, a), c, d, a, bp2long(m_in, 11) + 0x0895cd7beL, 22);

      a = MD5STEP(a, F1(b, c, d), b, c, d, bp2long(m_in, 12) + 0x06b901122L, 7);

      d = MD5STEP(d, F1(a, b, c), a, b, c, bp2long(m_in, 13) + 0x0fd987193L, 12);

      c = MD5STEP(c, F1(d, a, b), d, a, b, bp2long(m_in, 14) + 0x0a679438eL, 17);

      b = MD5STEP(b, F1(c, d, a), c, d, a, bp2long(m_in, 15) + 0x049b40821L, 22);



      a = MD5STEP(a, F2(b, c, d), b, c, d, bp2long(m_in,  1) + 0x0f61e2562L, 5);

      d = MD5STEP(d, F2(a, b, c), a, b, c, bp2long(m_in,  6) + 0x0c040b340L, 9);

      c = MD5STEP(c, F2(d, a, b), d, a, b, bp2long(m_in, 11) + 0x0265e5a51L, 14);

      b = MD5STEP(b, F2(c, d, a), c, d, a, bp2long(m_in,  0) + 0x0e9b6c7aaL, 20);

      a = MD5STEP(a, F2(b, c, d), b, c, d, bp2long(m_in,  5) + 0x0d62f105dL, 5);

      d = MD5STEP(d, F2(a, b, c), a, b, c, bp2long(m_in, 10) + 0x002441453L, 9);

      c = MD5STEP(c, F2(d, a, b), d, a, b, bp2long(m_in, 15) + 0x0d8a1e681L, 14);

      b = MD5STEP(b, F2(c, d, a), c, d, a, bp2long(m_in,  4) + 0x0e7d3fbc8L, 20);

      a = MD5STEP(a, F2(b, c, d), b, c, d, bp2long(m_in,  9) + 0x021e1cde6L, 5);

      d = MD5STEP(d, F2(a, b, c), a, b, c, bp2long(m_in, 14) + 0x0c33707d6L, 9);

      c = MD5STEP(c, F2(d, a, b), d, a, b, bp2long(m_in,  3) + 0x0f4d50d87L, 14);

      b = MD5STEP(b, F2(c, d, a), c, d, a, bp2long(m_in,  8) + 0x0455a14edL, 20);

      a = MD5STEP(a, F2(b, c, d), b, c, d, bp2long(m_in, 13) + 0x0a9e3e905L, 5);

      d = MD5STEP(d, F2(a, b, c), a, b, c, bp2long(m_in,  2) + 0x0fcefa3f8L, 9);

      c = MD5STEP(c, F2(d, a, b), d, a, b, bp2long(m_in,  7) + 0x0676f02d9L, 14);

      b = MD5STEP(b, F2(c, d, a), c, d, a, bp2long(m_in, 12) + 0x08d2a4c8aL, 20);



      a = MD5STEP(a, F3(b, c, d), b, c, d, bp2long(m_in,  5) + 0x0fffa3942L, 4);

      d = MD5STEP(d, F3(a, b, c), a, b, c, bp2long(m_in,  8) + 0x08771f681L, 11);

      c = MD5STEP(c, F3(d, a, b), d, a, b, bp2long(m_in, 11) + 0x06d9d6122L, 16);

      b = MD5STEP(b, F3(c, d, a), c, d, a, bp2long(m_in, 14) + 0x0fde5380cL, 23);

      a = MD5STEP(a, F3(b, c, d), b, c, d, bp2long(m_in,  1) + 0x0a4beea44L, 4);

      d = MD5STEP(d, F3(a, b, c), a, b, c, bp2long(m_in,  4) + 0x04bdecfa9L, 11);

      c = MD5STEP(c, F3(d, a, b), d, a, b, bp2long(m_in,  7) + 0x0f6bb4b60L, 16);

      b = MD5STEP(b, F3(c, d, a), c, d, a, bp2long(m_in, 10) + 0x0bebfbc70L, 23);

      a = MD5STEP(a, F3(b, c, d), b, c, d, bp2long(m_in, 13) + 0x0289b7ec6L, 4);

      d = MD5STEP(d, F3(a, b, c), a, b, c, bp2long(m_in,  0) + 0x0eaa127faL, 11);

      c = MD5STEP(c, F3(d, a, b), d, a, b, bp2long(m_in,  3) + 0x0d4ef3085L, 16);

      b = MD5STEP(b, F3(c, d, a), c, d, a, bp2long(m_in,  6) + 0x004881d05L, 23);

      a = MD5STEP(a, F3(b, c, d), b, c, d, bp2long(m_in,  9) + 0x0d9d4d039L, 4);

      d = MD5STEP(d, F3(a, b, c), a, b, c, bp2long(m_in, 12) + 0x0e6db99e5L, 11);

      c = MD5STEP(c, F3(d, a, b), d, a, b, bp2long(m_in, 15) + 0x01fa27cf8L, 16);

      b = MD5STEP(b, F3(c, d, a), c, d, a, bp2long(m_in,  2) + 0x0c4ac5665L, 23);



      a = MD5STEP(a, F4(b, c, d), b, c, d, bp2long(m_in,  0) + 0x0f4292244L, 6);

      d = MD5STEP(d, F4(a, b, c), a, b, c, bp2long(m_in,  7) + 0x0432aff97L, 10);

      c = MD5STEP(c, F4(d, a, b), d, a, b, bp2long(m_in, 14) + 0x0ab9423a7L, 15);

      b = MD5STEP(b, F4(c, d, a), c, d, a, bp2long(m_in,  5) + 0x0fc93a039L, 21);

      a = MD5STEP(a, F4(b, c, d), b, c, d, bp2long(m_in, 12) + 0x0655b59c3L, 6);

      d = MD5STEP(d, F4(a, b, c), a, b, c, bp2long(m_in,  3) + 0x08f0ccc92L, 10);

      c = MD5STEP(c, F4(d, a, b), d, a, b, bp2long(m_in, 10) + 0x0ffeff47dL, 15);

      b = MD5STEP(b, F4(c, d, a), c, d, a, bp2long(m_in,  1) + 0x085845dd1L, 21);

      a = MD5STEP(a, F4(b, c, d), b, c, d, bp2long(m_in,  8) + 0x06fa87e4fL, 6);

      d = MD5STEP(d, F4(a, b, c), a, b, c, bp2long(m_in, 15) + 0x0fe2ce6e0L, 10);

      c = MD5STEP(c, F4(d, a, b), d, a, b, bp2long(m_in,  6) + 0x0a3014314L, 15);

      b = MD5STEP(b, F4(c, d, a), c, d, a, bp2long(m_in, 13) + 0x04e0811a1L, 21);

      a = MD5STEP(a, F4(b, c, d), b, c, d, bp2long(m_in,  4) + 0x0f7537e82L, 6);

      d = MD5STEP(d, F4(a, b, c), a, b, c, bp2long(m_in, 11) + 0x0bd3af235L, 10);

      c = MD5STEP(c, F4(d, a, b), d, a, b, bp2long(m_in,  2) + 0x02ad7d2bbL, 15);

      b = MD5STEP(b, F4(c, d, a), c, d, a, bp2long(m_in,  9) + 0x0eb86d391L, 21);



      m_buf[0] += a;

      m_buf[1] += b;

      m_buf[2] += c;

      m_buf[3] += d;

  }

}







