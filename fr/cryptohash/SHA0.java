/*   1:    */ package fr.cryptohash;
/*   2:    */ 
/*   3:    */ public class SHA0
/*   4:    */   extends MDHelper
/*   5:    */ {
/*   6:    */   private int[] currentVal;
/*   7:    */   
/*   8:    */   public SHA0()
/*   9:    */   {
/*  10: 48 */     super(false, 8);
/*  11:    */   }
/*  12:    */   
/*  13:    */   public Digest copy()
/*  14:    */   {
/*  15: 56 */     SHA0 localSHA0 = new SHA0();
/*  16: 57 */     System.arraycopy(this.currentVal, 0, localSHA0.currentVal, 0, this.currentVal.length);
/*  17:    */     
/*  18: 59 */     return copyState(localSHA0);
/*  19:    */   }
/*  20:    */   
/*  21:    */   public int getDigestLength()
/*  22:    */   {
/*  23: 65 */     return 20;
/*  24:    */   }
/*  25:    */   
/*  26:    */   public int getBlockLength()
/*  27:    */   {
/*  28: 71 */     return 64;
/*  29:    */   }
/*  30:    */   
/*  31:    */   protected void engineReset()
/*  32:    */   {
/*  33: 77 */     this.currentVal[0] = 1732584193;
/*  34: 78 */     this.currentVal[1] = -271733879;
/*  35: 79 */     this.currentVal[2] = -1732584194;
/*  36: 80 */     this.currentVal[3] = 271733878;
/*  37: 81 */     this.currentVal[4] = -1009589776;
/*  38:    */   }
/*  39:    */   
/*  40:    */   protected void doPadding(byte[] paramArrayOfByte, int paramInt)
/*  41:    */   {
/*  42: 87 */     makeMDPadding();
/*  43: 88 */     for (int i = 0; i < 5; i++) {
/*  44: 89 */       encodeBEInt(this.currentVal[i], paramArrayOfByte, paramInt + 4 * i);
/*  45:    */     }
/*  46:    */   }
/*  47:    */   
/*  48:    */   protected void doInit()
/*  49:    */   {
/*  50: 96 */     this.currentVal = new int[5];
/*  51: 97 */     engineReset();
/*  52:    */   }
/*  53:    */   
/*  54:    */   private static final void encodeBEInt(int paramInt1, byte[] paramArrayOfByte, int paramInt2)
/*  55:    */   {
/*  56:111 */     paramArrayOfByte[(paramInt2 + 0)] = ((byte)(paramInt1 >>> 24));
/*  57:112 */     paramArrayOfByte[(paramInt2 + 1)] = ((byte)(paramInt1 >>> 16));
/*  58:113 */     paramArrayOfByte[(paramInt2 + 2)] = ((byte)(paramInt1 >>> 8));
/*  59:114 */     paramArrayOfByte[(paramInt2 + 3)] = ((byte)paramInt1);
/*  60:    */   }
/*  61:    */   
/*  62:    */   private static final int decodeBEInt(byte[] paramArrayOfByte, int paramInt)
/*  63:    */   {
/*  64:127 */     return (paramArrayOfByte[paramInt] & 0xFF) << 24 | (paramArrayOfByte[(paramInt + 1)] & 0xFF) << 16 | (paramArrayOfByte[(paramInt + 2)] & 0xFF) << 8 | paramArrayOfByte[(paramInt + 3)] & 0xFF;
/*  65:    */   }
/*  66:    */   
/*  67:    */   protected void processBlock(byte[] paramArrayOfByte)
/*  68:    */   {
/*  69:136 */     int i = this.currentVal[0];int j = this.currentVal[1];
/*  70:137 */     int k = this.currentVal[2];int m = this.currentVal[3];int n = this.currentVal[4];
/*  71:    */     
/*  72:139 */     int i1 = decodeBEInt(paramArrayOfByte, 0);
/*  73:140 */     n = (i << 5 | i >>> 27) + (j & k | (j ^ 0xFFFFFFFF) & m) + n + i1 + 1518500249;
/*  74:    */     
/*  75:142 */     j = j << 30 | j >>> 2;
/*  76:143 */     int i2 = decodeBEInt(paramArrayOfByte, 4);
/*  77:144 */     m = (n << 5 | n >>> 27) + (i & j | (i ^ 0xFFFFFFFF) & k) + m + i2 + 1518500249;
/*  78:    */     
/*  79:146 */     i = i << 30 | i >>> 2;
/*  80:147 */     int i3 = decodeBEInt(paramArrayOfByte, 8);
/*  81:148 */     k = (m << 5 | m >>> 27) + (n & i | (n ^ 0xFFFFFFFF) & j) + k + i3 + 1518500249;
/*  82:    */     
/*  83:150 */     n = n << 30 | n >>> 2;
/*  84:151 */     int i4 = decodeBEInt(paramArrayOfByte, 12);
/*  85:152 */     j = (k << 5 | k >>> 27) + (m & n | (m ^ 0xFFFFFFFF) & i) + j + i4 + 1518500249;
/*  86:    */     
/*  87:154 */     m = m << 30 | m >>> 2;
/*  88:155 */     int i5 = decodeBEInt(paramArrayOfByte, 16);
/*  89:156 */     i = (j << 5 | j >>> 27) + (k & m | (k ^ 0xFFFFFFFF) & n) + i + i5 + 1518500249;
/*  90:    */     
/*  91:158 */     k = k << 30 | k >>> 2;
/*  92:159 */     int i6 = decodeBEInt(paramArrayOfByte, 20);
/*  93:160 */     n = (i << 5 | i >>> 27) + (j & k | (j ^ 0xFFFFFFFF) & m) + n + i6 + 1518500249;
/*  94:    */     
/*  95:162 */     j = j << 30 | j >>> 2;
/*  96:163 */     int i7 = decodeBEInt(paramArrayOfByte, 24);
/*  97:164 */     m = (n << 5 | n >>> 27) + (i & j | (i ^ 0xFFFFFFFF) & k) + m + i7 + 1518500249;
/*  98:    */     
/*  99:166 */     i = i << 30 | i >>> 2;
/* 100:167 */     int i8 = decodeBEInt(paramArrayOfByte, 28);
/* 101:168 */     k = (m << 5 | m >>> 27) + (n & i | (n ^ 0xFFFFFFFF) & j) + k + i8 + 1518500249;
/* 102:    */     
/* 103:170 */     n = n << 30 | n >>> 2;
/* 104:171 */     int i9 = decodeBEInt(paramArrayOfByte, 32);
/* 105:172 */     j = (k << 5 | k >>> 27) + (m & n | (m ^ 0xFFFFFFFF) & i) + j + i9 + 1518500249;
/* 106:    */     
/* 107:174 */     m = m << 30 | m >>> 2;
/* 108:175 */     int i10 = decodeBEInt(paramArrayOfByte, 36);
/* 109:176 */     i = (j << 5 | j >>> 27) + (k & m | (k ^ 0xFFFFFFFF) & n) + i + i10 + 1518500249;
/* 110:    */     
/* 111:178 */     k = k << 30 | k >>> 2;
/* 112:179 */     int i11 = decodeBEInt(paramArrayOfByte, 40);
/* 113:180 */     n = (i << 5 | i >>> 27) + (j & k | (j ^ 0xFFFFFFFF) & m) + n + i11 + 1518500249;
/* 114:    */     
/* 115:182 */     j = j << 30 | j >>> 2;
/* 116:183 */     int i12 = decodeBEInt(paramArrayOfByte, 44);
/* 117:184 */     m = (n << 5 | n >>> 27) + (i & j | (i ^ 0xFFFFFFFF) & k) + m + i12 + 1518500249;
/* 118:    */     
/* 119:186 */     i = i << 30 | i >>> 2;
/* 120:187 */     int i13 = decodeBEInt(paramArrayOfByte, 48);
/* 121:188 */     k = (m << 5 | m >>> 27) + (n & i | (n ^ 0xFFFFFFFF) & j) + k + i13 + 1518500249;
/* 122:    */     
/* 123:190 */     n = n << 30 | n >>> 2;
/* 124:191 */     int i14 = decodeBEInt(paramArrayOfByte, 52);
/* 125:192 */     j = (k << 5 | k >>> 27) + (m & n | (m ^ 0xFFFFFFFF) & i) + j + i14 + 1518500249;
/* 126:    */     
/* 127:194 */     m = m << 30 | m >>> 2;
/* 128:195 */     int i15 = decodeBEInt(paramArrayOfByte, 56);
/* 129:196 */     i = (j << 5 | j >>> 27) + (k & m | (k ^ 0xFFFFFFFF) & n) + i + i15 + 1518500249;
/* 130:    */     
/* 131:198 */     k = k << 30 | k >>> 2;
/* 132:199 */     int i16 = decodeBEInt(paramArrayOfByte, 60);
/* 133:200 */     n = (i << 5 | i >>> 27) + (j & k | (j ^ 0xFFFFFFFF) & m) + n + i16 + 1518500249;
/* 134:    */     
/* 135:202 */     j = j << 30 | j >>> 2;
/* 136:203 */     i1 = i14 ^ i9 ^ i3 ^ i1;
/* 137:204 */     m = (n << 5 | n >>> 27) + (i & j | (i ^ 0xFFFFFFFF) & k) + m + i1 + 1518500249;
/* 138:    */     
/* 139:206 */     i = i << 30 | i >>> 2;
/* 140:207 */     i2 = i15 ^ i10 ^ i4 ^ i2;
/* 141:208 */     k = (m << 5 | m >>> 27) + (n & i | (n ^ 0xFFFFFFFF) & j) + k + i2 + 1518500249;
/* 142:    */     
/* 143:210 */     n = n << 30 | n >>> 2;
/* 144:211 */     i3 = i16 ^ i11 ^ i5 ^ i3;
/* 145:212 */     j = (k << 5 | k >>> 27) + (m & n | (m ^ 0xFFFFFFFF) & i) + j + i3 + 1518500249;
/* 146:    */     
/* 147:214 */     m = m << 30 | m >>> 2;
/* 148:215 */     i4 = i1 ^ i12 ^ i6 ^ i4;
/* 149:216 */     i = (j << 5 | j >>> 27) + (k & m | (k ^ 0xFFFFFFFF) & n) + i + i4 + 1518500249;
/* 150:    */     
/* 151:218 */     k = k << 30 | k >>> 2;
/* 152:219 */     i5 = i2 ^ i13 ^ i7 ^ i5;
/* 153:220 */     n = (i << 5 | i >>> 27) + (j ^ k ^ m) + n + i5 + 1859775393;
/* 154:    */     
/* 155:222 */     j = j << 30 | j >>> 2;
/* 156:223 */     i6 = i3 ^ i14 ^ i8 ^ i6;
/* 157:224 */     m = (n << 5 | n >>> 27) + (i ^ j ^ k) + m + i6 + 1859775393;
/* 158:    */     
/* 159:226 */     i = i << 30 | i >>> 2;
/* 160:227 */     i7 = i4 ^ i15 ^ i9 ^ i7;
/* 161:228 */     k = (m << 5 | m >>> 27) + (n ^ i ^ j) + k + i7 + 1859775393;
/* 162:    */     
/* 163:230 */     n = n << 30 | n >>> 2;
/* 164:231 */     i8 = i5 ^ i16 ^ i10 ^ i8;
/* 165:232 */     j = (k << 5 | k >>> 27) + (m ^ n ^ i) + j + i8 + 1859775393;
/* 166:    */     
/* 167:234 */     m = m << 30 | m >>> 2;
/* 168:235 */     i9 = i6 ^ i1 ^ i11 ^ i9;
/* 169:236 */     i = (j << 5 | j >>> 27) + (k ^ m ^ n) + i + i9 + 1859775393;
/* 170:    */     
/* 171:238 */     k = k << 30 | k >>> 2;
/* 172:239 */     i10 = i7 ^ i2 ^ i12 ^ i10;
/* 173:240 */     n = (i << 5 | i >>> 27) + (j ^ k ^ m) + n + i10 + 1859775393;
/* 174:    */     
/* 175:242 */     j = j << 30 | j >>> 2;
/* 176:243 */     i11 = i8 ^ i3 ^ i13 ^ i11;
/* 177:244 */     m = (n << 5 | n >>> 27) + (i ^ j ^ k) + m + i11 + 1859775393;
/* 178:    */     
/* 179:246 */     i = i << 30 | i >>> 2;
/* 180:247 */     i12 = i9 ^ i4 ^ i14 ^ i12;
/* 181:248 */     k = (m << 5 | m >>> 27) + (n ^ i ^ j) + k + i12 + 1859775393;
/* 182:    */     
/* 183:250 */     n = n << 30 | n >>> 2;
/* 184:251 */     i13 = i10 ^ i5 ^ i15 ^ i13;
/* 185:252 */     j = (k << 5 | k >>> 27) + (m ^ n ^ i) + j + i13 + 1859775393;
/* 186:    */     
/* 187:254 */     m = m << 30 | m >>> 2;
/* 188:255 */     i14 = i11 ^ i6 ^ i16 ^ i14;
/* 189:256 */     i = (j << 5 | j >>> 27) + (k ^ m ^ n) + i + i14 + 1859775393;
/* 190:    */     
/* 191:258 */     k = k << 30 | k >>> 2;
/* 192:259 */     i15 = i12 ^ i7 ^ i1 ^ i15;
/* 193:260 */     n = (i << 5 | i >>> 27) + (j ^ k ^ m) + n + i15 + 1859775393;
/* 194:    */     
/* 195:262 */     j = j << 30 | j >>> 2;
/* 196:263 */     i16 = i13 ^ i8 ^ i2 ^ i16;
/* 197:264 */     m = (n << 5 | n >>> 27) + (i ^ j ^ k) + m + i16 + 1859775393;
/* 198:    */     
/* 199:266 */     i = i << 30 | i >>> 2;
/* 200:267 */     i1 = i14 ^ i9 ^ i3 ^ i1;
/* 201:268 */     k = (m << 5 | m >>> 27) + (n ^ i ^ j) + k + i1 + 1859775393;
/* 202:    */     
/* 203:270 */     n = n << 30 | n >>> 2;
/* 204:271 */     i2 = i15 ^ i10 ^ i4 ^ i2;
/* 205:272 */     j = (k << 5 | k >>> 27) + (m ^ n ^ i) + j + i2 + 1859775393;
/* 206:    */     
/* 207:274 */     m = m << 30 | m >>> 2;
/* 208:275 */     i3 = i16 ^ i11 ^ i5 ^ i3;
/* 209:276 */     i = (j << 5 | j >>> 27) + (k ^ m ^ n) + i + i3 + 1859775393;
/* 210:    */     
/* 211:278 */     k = k << 30 | k >>> 2;
/* 212:279 */     i4 = i1 ^ i12 ^ i6 ^ i4;
/* 213:280 */     n = (i << 5 | i >>> 27) + (j ^ k ^ m) + n + i4 + 1859775393;
/* 214:    */     
/* 215:282 */     j = j << 30 | j >>> 2;
/* 216:283 */     i5 = i2 ^ i13 ^ i7 ^ i5;
/* 217:284 */     m = (n << 5 | n >>> 27) + (i ^ j ^ k) + m + i5 + 1859775393;
/* 218:    */     
/* 219:286 */     i = i << 30 | i >>> 2;
/* 220:287 */     i6 = i3 ^ i14 ^ i8 ^ i6;
/* 221:288 */     k = (m << 5 | m >>> 27) + (n ^ i ^ j) + k + i6 + 1859775393;
/* 222:    */     
/* 223:290 */     n = n << 30 | n >>> 2;
/* 224:291 */     i7 = i4 ^ i15 ^ i9 ^ i7;
/* 225:292 */     j = (k << 5 | k >>> 27) + (m ^ n ^ i) + j + i7 + 1859775393;
/* 226:    */     
/* 227:294 */     m = m << 30 | m >>> 2;
/* 228:295 */     i8 = i5 ^ i16 ^ i10 ^ i8;
/* 229:296 */     i = (j << 5 | j >>> 27) + (k ^ m ^ n) + i + i8 + 1859775393;
/* 230:    */     
/* 231:298 */     k = k << 30 | k >>> 2;
/* 232:299 */     i9 = i6 ^ i1 ^ i11 ^ i9;
/* 233:300 */     n = (i << 5 | i >>> 27) + (j & k | j & m | k & m) + n + i9 + -1894007588;
/* 234:    */     
/* 235:302 */     j = j << 30 | j >>> 2;
/* 236:303 */     i10 = i7 ^ i2 ^ i12 ^ i10;
/* 237:304 */     m = (n << 5 | n >>> 27) + (i & j | i & k | j & k) + m + i10 + -1894007588;
/* 238:    */     
/* 239:306 */     i = i << 30 | i >>> 2;
/* 240:307 */     i11 = i8 ^ i3 ^ i13 ^ i11;
/* 241:308 */     k = (m << 5 | m >>> 27) + (n & i | n & j | i & j) + k + i11 + -1894007588;
/* 242:    */     
/* 243:310 */     n = n << 30 | n >>> 2;
/* 244:311 */     i12 = i9 ^ i4 ^ i14 ^ i12;
/* 245:312 */     j = (k << 5 | k >>> 27) + (m & n | m & i | n & i) + j + i12 + -1894007588;
/* 246:    */     
/* 247:314 */     m = m << 30 | m >>> 2;
/* 248:315 */     i13 = i10 ^ i5 ^ i15 ^ i13;
/* 249:316 */     i = (j << 5 | j >>> 27) + (k & m | k & n | m & n) + i + i13 + -1894007588;
/* 250:    */     
/* 251:318 */     k = k << 30 | k >>> 2;
/* 252:319 */     i14 = i11 ^ i6 ^ i16 ^ i14;
/* 253:320 */     n = (i << 5 | i >>> 27) + (j & k | j & m | k & m) + n + i14 + -1894007588;
/* 254:    */     
/* 255:322 */     j = j << 30 | j >>> 2;
/* 256:323 */     i15 = i12 ^ i7 ^ i1 ^ i15;
/* 257:324 */     m = (n << 5 | n >>> 27) + (i & j | i & k | j & k) + m + i15 + -1894007588;
/* 258:    */     
/* 259:326 */     i = i << 30 | i >>> 2;
/* 260:327 */     i16 = i13 ^ i8 ^ i2 ^ i16;
/* 261:328 */     k = (m << 5 | m >>> 27) + (n & i | n & j | i & j) + k + i16 + -1894007588;
/* 262:    */     
/* 263:330 */     n = n << 30 | n >>> 2;
/* 264:331 */     i1 = i14 ^ i9 ^ i3 ^ i1;
/* 265:332 */     j = (k << 5 | k >>> 27) + (m & n | m & i | n & i) + j + i1 + -1894007588;
/* 266:    */     
/* 267:334 */     m = m << 30 | m >>> 2;
/* 268:335 */     i2 = i15 ^ i10 ^ i4 ^ i2;
/* 269:336 */     i = (j << 5 | j >>> 27) + (k & m | k & n | m & n) + i + i2 + -1894007588;
/* 270:    */     
/* 271:338 */     k = k << 30 | k >>> 2;
/* 272:339 */     i3 = i16 ^ i11 ^ i5 ^ i3;
/* 273:340 */     n = (i << 5 | i >>> 27) + (j & k | j & m | k & m) + n + i3 + -1894007588;
/* 274:    */     
/* 275:342 */     j = j << 30 | j >>> 2;
/* 276:343 */     i4 = i1 ^ i12 ^ i6 ^ i4;
/* 277:344 */     m = (n << 5 | n >>> 27) + (i & j | i & k | j & k) + m + i4 + -1894007588;
/* 278:    */     
/* 279:346 */     i = i << 30 | i >>> 2;
/* 280:347 */     i5 = i2 ^ i13 ^ i7 ^ i5;
/* 281:348 */     k = (m << 5 | m >>> 27) + (n & i | n & j | i & j) + k + i5 + -1894007588;
/* 282:    */     
/* 283:350 */     n = n << 30 | n >>> 2;
/* 284:351 */     i6 = i3 ^ i14 ^ i8 ^ i6;
/* 285:352 */     j = (k << 5 | k >>> 27) + (m & n | m & i | n & i) + j + i6 + -1894007588;
/* 286:    */     
/* 287:354 */     m = m << 30 | m >>> 2;
/* 288:355 */     i7 = i4 ^ i15 ^ i9 ^ i7;
/* 289:356 */     i = (j << 5 | j >>> 27) + (k & m | k & n | m & n) + i + i7 + -1894007588;
/* 290:    */     
/* 291:358 */     k = k << 30 | k >>> 2;
/* 292:359 */     i8 = i5 ^ i16 ^ i10 ^ i8;
/* 293:360 */     n = (i << 5 | i >>> 27) + (j & k | j & m | k & m) + n + i8 + -1894007588;
/* 294:    */     
/* 295:362 */     j = j << 30 | j >>> 2;
/* 296:363 */     i9 = i6 ^ i1 ^ i11 ^ i9;
/* 297:364 */     m = (n << 5 | n >>> 27) + (i & j | i & k | j & k) + m + i9 + -1894007588;
/* 298:    */     
/* 299:366 */     i = i << 30 | i >>> 2;
/* 300:367 */     i10 = i7 ^ i2 ^ i12 ^ i10;
/* 301:368 */     k = (m << 5 | m >>> 27) + (n & i | n & j | i & j) + k + i10 + -1894007588;
/* 302:    */     
/* 303:370 */     n = n << 30 | n >>> 2;
/* 304:371 */     i11 = i8 ^ i3 ^ i13 ^ i11;
/* 305:372 */     j = (k << 5 | k >>> 27) + (m & n | m & i | n & i) + j + i11 + -1894007588;
/* 306:    */     
/* 307:374 */     m = m << 30 | m >>> 2;
/* 308:375 */     i12 = i9 ^ i4 ^ i14 ^ i12;
/* 309:376 */     i = (j << 5 | j >>> 27) + (k & m | k & n | m & n) + i + i12 + -1894007588;
/* 310:    */     
/* 311:378 */     k = k << 30 | k >>> 2;
/* 312:379 */     i13 = i10 ^ i5 ^ i15 ^ i13;
/* 313:380 */     n = (i << 5 | i >>> 27) + (j ^ k ^ m) + n + i13 + -899497514;
/* 314:    */     
/* 315:382 */     j = j << 30 | j >>> 2;
/* 316:383 */     i14 = i11 ^ i6 ^ i16 ^ i14;
/* 317:384 */     m = (n << 5 | n >>> 27) + (i ^ j ^ k) + m + i14 + -899497514;
/* 318:    */     
/* 319:386 */     i = i << 30 | i >>> 2;
/* 320:387 */     i15 = i12 ^ i7 ^ i1 ^ i15;
/* 321:388 */     k = (m << 5 | m >>> 27) + (n ^ i ^ j) + k + i15 + -899497514;
/* 322:    */     
/* 323:390 */     n = n << 30 | n >>> 2;
/* 324:391 */     i16 = i13 ^ i8 ^ i2 ^ i16;
/* 325:392 */     j = (k << 5 | k >>> 27) + (m ^ n ^ i) + j + i16 + -899497514;
/* 326:    */     
/* 327:394 */     m = m << 30 | m >>> 2;
/* 328:395 */     i1 = i14 ^ i9 ^ i3 ^ i1;
/* 329:396 */     i = (j << 5 | j >>> 27) + (k ^ m ^ n) + i + i1 + -899497514;
/* 330:    */     
/* 331:398 */     k = k << 30 | k >>> 2;
/* 332:399 */     i2 = i15 ^ i10 ^ i4 ^ i2;
/* 333:400 */     n = (i << 5 | i >>> 27) + (j ^ k ^ m) + n + i2 + -899497514;
/* 334:    */     
/* 335:402 */     j = j << 30 | j >>> 2;
/* 336:403 */     i3 = i16 ^ i11 ^ i5 ^ i3;
/* 337:404 */     m = (n << 5 | n >>> 27) + (i ^ j ^ k) + m + i3 + -899497514;
/* 338:    */     
/* 339:406 */     i = i << 30 | i >>> 2;
/* 340:407 */     i4 = i1 ^ i12 ^ i6 ^ i4;
/* 341:408 */     k = (m << 5 | m >>> 27) + (n ^ i ^ j) + k + i4 + -899497514;
/* 342:    */     
/* 343:410 */     n = n << 30 | n >>> 2;
/* 344:411 */     i5 = i2 ^ i13 ^ i7 ^ i5;
/* 345:412 */     j = (k << 5 | k >>> 27) + (m ^ n ^ i) + j + i5 + -899497514;
/* 346:    */     
/* 347:414 */     m = m << 30 | m >>> 2;
/* 348:415 */     i6 = i3 ^ i14 ^ i8 ^ i6;
/* 349:416 */     i = (j << 5 | j >>> 27) + (k ^ m ^ n) + i + i6 + -899497514;
/* 350:    */     
/* 351:418 */     k = k << 30 | k >>> 2;
/* 352:419 */     i7 = i4 ^ i15 ^ i9 ^ i7;
/* 353:420 */     n = (i << 5 | i >>> 27) + (j ^ k ^ m) + n + i7 + -899497514;
/* 354:    */     
/* 355:422 */     j = j << 30 | j >>> 2;
/* 356:423 */     i8 = i5 ^ i16 ^ i10 ^ i8;
/* 357:424 */     m = (n << 5 | n >>> 27) + (i ^ j ^ k) + m + i8 + -899497514;
/* 358:    */     
/* 359:426 */     i = i << 30 | i >>> 2;
/* 360:427 */     i9 = i6 ^ i1 ^ i11 ^ i9;
/* 361:428 */     k = (m << 5 | m >>> 27) + (n ^ i ^ j) + k + i9 + -899497514;
/* 362:    */     
/* 363:430 */     n = n << 30 | n >>> 2;
/* 364:431 */     i10 = i7 ^ i2 ^ i12 ^ i10;
/* 365:432 */     j = (k << 5 | k >>> 27) + (m ^ n ^ i) + j + i10 + -899497514;
/* 366:    */     
/* 367:434 */     m = m << 30 | m >>> 2;
/* 368:435 */     i11 = i8 ^ i3 ^ i13 ^ i11;
/* 369:436 */     i = (j << 5 | j >>> 27) + (k ^ m ^ n) + i + i11 + -899497514;
/* 370:    */     
/* 371:438 */     k = k << 30 | k >>> 2;
/* 372:439 */     i12 = i9 ^ i4 ^ i14 ^ i12;
/* 373:440 */     n = (i << 5 | i >>> 27) + (j ^ k ^ m) + n + i12 + -899497514;
/* 374:    */     
/* 375:442 */     j = j << 30 | j >>> 2;
/* 376:443 */     i13 = i10 ^ i5 ^ i15 ^ i13;
/* 377:444 */     m = (n << 5 | n >>> 27) + (i ^ j ^ k) + m + i13 + -899497514;
/* 378:    */     
/* 379:446 */     i = i << 30 | i >>> 2;
/* 380:447 */     i14 = i11 ^ i6 ^ i16 ^ i14;
/* 381:448 */     k = (m << 5 | m >>> 27) + (n ^ i ^ j) + k + i14 + -899497514;
/* 382:    */     
/* 383:450 */     n = n << 30 | n >>> 2;
/* 384:451 */     i15 = i12 ^ i7 ^ i1 ^ i15;
/* 385:452 */     j = (k << 5 | k >>> 27) + (m ^ n ^ i) + j + i15 + -899497514;
/* 386:    */     
/* 387:454 */     m = m << 30 | m >>> 2;
/* 388:455 */     i16 = i13 ^ i8 ^ i2 ^ i16;
/* 389:456 */     i = (j << 5 | j >>> 27) + (k ^ m ^ n) + i + i16 + -899497514;
/* 390:    */     
/* 391:458 */     k = k << 30 | k >>> 2;
/* 392:    */     
/* 393:460 */     this.currentVal[0] += i;
/* 394:461 */     this.currentVal[1] += j;
/* 395:462 */     this.currentVal[2] += k;
/* 396:463 */     this.currentVal[3] += m;
/* 397:464 */     this.currentVal[4] += n;
/* 398:    */   }
/* 399:    */   
/* 400:    */   public String toString()
/* 401:    */   {
/* 402:470 */     return "SHA-0";
/* 403:    */   }
/* 404:    */ }


/* Location:           E:\java decompi;er\burst.jar
 * Qualified Name:     fr.cryptohash.SHA0
 * JD-Core Version:    0.7.1
 */