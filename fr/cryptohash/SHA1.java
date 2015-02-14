/*   1:    */ package fr.cryptohash;
/*   2:    */ 
/*   3:    */ public class SHA1
/*   4:    */   extends MDHelper
/*   5:    */ {
/*   6:    */   private int[] currentVal;
/*   7:    */   
/*   8:    */   public SHA1()
/*   9:    */   {
/*  10: 47 */     super(false, 8);
/*  11:    */   }
/*  12:    */   
/*  13:    */   public Digest copy()
/*  14:    */   {
/*  15: 55 */     SHA1 localSHA1 = new SHA1();
/*  16: 56 */     System.arraycopy(this.currentVal, 0, localSHA1.currentVal, 0, this.currentVal.length);
/*  17:    */     
/*  18: 58 */     return copyState(localSHA1);
/*  19:    */   }
/*  20:    */   
/*  21:    */   public int getDigestLength()
/*  22:    */   {
/*  23: 64 */     return 20;
/*  24:    */   }
/*  25:    */   
/*  26:    */   public int getBlockLength()
/*  27:    */   {
/*  28: 70 */     return 64;
/*  29:    */   }
/*  30:    */   
/*  31:    */   protected void engineReset()
/*  32:    */   {
/*  33: 76 */     this.currentVal[0] = 1732584193;
/*  34: 77 */     this.currentVal[1] = -271733879;
/*  35: 78 */     this.currentVal[2] = -1732584194;
/*  36: 79 */     this.currentVal[3] = 271733878;
/*  37: 80 */     this.currentVal[4] = -1009589776;
/*  38:    */   }
/*  39:    */   
/*  40:    */   protected void doPadding(byte[] paramArrayOfByte, int paramInt)
/*  41:    */   {
/*  42: 86 */     makeMDPadding();
/*  43: 87 */     for (int i = 0; i < 5; i++) {
/*  44: 88 */       encodeBEInt(this.currentVal[i], paramArrayOfByte, paramInt + 4 * i);
/*  45:    */     }
/*  46:    */   }
/*  47:    */   
/*  48:    */   protected void doInit()
/*  49:    */   {
/*  50: 95 */     this.currentVal = new int[5];
/*  51: 96 */     engineReset();
/*  52:    */   }
/*  53:    */   
/*  54:    */   private static final void encodeBEInt(int paramInt1, byte[] paramArrayOfByte, int paramInt2)
/*  55:    */   {
/*  56:110 */     paramArrayOfByte[(paramInt2 + 0)] = ((byte)(paramInt1 >>> 24));
/*  57:111 */     paramArrayOfByte[(paramInt2 + 1)] = ((byte)(paramInt1 >>> 16));
/*  58:112 */     paramArrayOfByte[(paramInt2 + 2)] = ((byte)(paramInt1 >>> 8));
/*  59:113 */     paramArrayOfByte[(paramInt2 + 3)] = ((byte)paramInt1);
/*  60:    */   }
/*  61:    */   
/*  62:    */   private static final int decodeBEInt(byte[] paramArrayOfByte, int paramInt)
/*  63:    */   {
/*  64:126 */     return (paramArrayOfByte[paramInt] & 0xFF) << 24 | (paramArrayOfByte[(paramInt + 1)] & 0xFF) << 16 | (paramArrayOfByte[(paramInt + 2)] & 0xFF) << 8 | paramArrayOfByte[(paramInt + 3)] & 0xFF;
/*  65:    */   }
/*  66:    */   
/*  67:    */   protected void processBlock(byte[] paramArrayOfByte)
/*  68:    */   {
/*  69:135 */     int i = this.currentVal[0];int j = this.currentVal[1];
/*  70:136 */     int k = this.currentVal[2];int m = this.currentVal[3];int n = this.currentVal[4];
/*  71:    */     
/*  72:    */ 
/*  73:139 */     int i2 = decodeBEInt(paramArrayOfByte, 0);
/*  74:140 */     n = (i << 5 | i >>> 27) + (j & k | (j ^ 0xFFFFFFFF) & m) + n + i2 + 1518500249;
/*  75:    */     
/*  76:142 */     j = j << 30 | j >>> 2;
/*  77:143 */     int i3 = decodeBEInt(paramArrayOfByte, 4);
/*  78:144 */     m = (n << 5 | n >>> 27) + (i & j | (i ^ 0xFFFFFFFF) & k) + m + i3 + 1518500249;
/*  79:    */     
/*  80:146 */     i = i << 30 | i >>> 2;
/*  81:147 */     int i4 = decodeBEInt(paramArrayOfByte, 8);
/*  82:148 */     k = (m << 5 | m >>> 27) + (n & i | (n ^ 0xFFFFFFFF) & j) + k + i4 + 1518500249;
/*  83:    */     
/*  84:150 */     n = n << 30 | n >>> 2;
/*  85:151 */     int i5 = decodeBEInt(paramArrayOfByte, 12);
/*  86:152 */     j = (k << 5 | k >>> 27) + (m & n | (m ^ 0xFFFFFFFF) & i) + j + i5 + 1518500249;
/*  87:    */     
/*  88:154 */     m = m << 30 | m >>> 2;
/*  89:155 */     int i6 = decodeBEInt(paramArrayOfByte, 16);
/*  90:156 */     i = (j << 5 | j >>> 27) + (k & m | (k ^ 0xFFFFFFFF) & n) + i + i6 + 1518500249;
/*  91:    */     
/*  92:158 */     k = k << 30 | k >>> 2;
/*  93:159 */     int i7 = decodeBEInt(paramArrayOfByte, 20);
/*  94:160 */     n = (i << 5 | i >>> 27) + (j & k | (j ^ 0xFFFFFFFF) & m) + n + i7 + 1518500249;
/*  95:    */     
/*  96:162 */     j = j << 30 | j >>> 2;
/*  97:163 */     int i8 = decodeBEInt(paramArrayOfByte, 24);
/*  98:164 */     m = (n << 5 | n >>> 27) + (i & j | (i ^ 0xFFFFFFFF) & k) + m + i8 + 1518500249;
/*  99:    */     
/* 100:166 */     i = i << 30 | i >>> 2;
/* 101:167 */     int i9 = decodeBEInt(paramArrayOfByte, 28);
/* 102:168 */     k = (m << 5 | m >>> 27) + (n & i | (n ^ 0xFFFFFFFF) & j) + k + i9 + 1518500249;
/* 103:    */     
/* 104:170 */     n = n << 30 | n >>> 2;
/* 105:171 */     int i10 = decodeBEInt(paramArrayOfByte, 32);
/* 106:172 */     j = (k << 5 | k >>> 27) + (m & n | (m ^ 0xFFFFFFFF) & i) + j + i10 + 1518500249;
/* 107:    */     
/* 108:174 */     m = m << 30 | m >>> 2;
/* 109:175 */     int i11 = decodeBEInt(paramArrayOfByte, 36);
/* 110:176 */     i = (j << 5 | j >>> 27) + (k & m | (k ^ 0xFFFFFFFF) & n) + i + i11 + 1518500249;
/* 111:    */     
/* 112:178 */     k = k << 30 | k >>> 2;
/* 113:179 */     int i12 = decodeBEInt(paramArrayOfByte, 40);
/* 114:180 */     n = (i << 5 | i >>> 27) + (j & k | (j ^ 0xFFFFFFFF) & m) + n + i12 + 1518500249;
/* 115:    */     
/* 116:182 */     j = j << 30 | j >>> 2;
/* 117:183 */     int i13 = decodeBEInt(paramArrayOfByte, 44);
/* 118:184 */     m = (n << 5 | n >>> 27) + (i & j | (i ^ 0xFFFFFFFF) & k) + m + i13 + 1518500249;
/* 119:    */     
/* 120:186 */     i = i << 30 | i >>> 2;
/* 121:187 */     int i14 = decodeBEInt(paramArrayOfByte, 48);
/* 122:188 */     k = (m << 5 | m >>> 27) + (n & i | (n ^ 0xFFFFFFFF) & j) + k + i14 + 1518500249;
/* 123:    */     
/* 124:190 */     n = n << 30 | n >>> 2;
/* 125:191 */     int i15 = decodeBEInt(paramArrayOfByte, 52);
/* 126:192 */     j = (k << 5 | k >>> 27) + (m & n | (m ^ 0xFFFFFFFF) & i) + j + i15 + 1518500249;
/* 127:    */     
/* 128:194 */     m = m << 30 | m >>> 2;
/* 129:195 */     int i16 = decodeBEInt(paramArrayOfByte, 56);
/* 130:196 */     i = (j << 5 | j >>> 27) + (k & m | (k ^ 0xFFFFFFFF) & n) + i + i16 + 1518500249;
/* 131:    */     
/* 132:198 */     k = k << 30 | k >>> 2;
/* 133:199 */     int i17 = decodeBEInt(paramArrayOfByte, 60);
/* 134:200 */     n = (i << 5 | i >>> 27) + (j & k | (j ^ 0xFFFFFFFF) & m) + n + i17 + 1518500249;
/* 135:    */     
/* 136:202 */     j = j << 30 | j >>> 2;
/* 137:203 */     int i1 = i15 ^ i10 ^ i4 ^ i2;
/* 138:204 */     i2 = i1 << 1 | i1 >>> 31;
/* 139:205 */     m = (n << 5 | n >>> 27) + (i & j | (i ^ 0xFFFFFFFF) & k) + m + i2 + 1518500249;
/* 140:    */     
/* 141:207 */     i = i << 30 | i >>> 2;
/* 142:208 */     i1 = i16 ^ i11 ^ i5 ^ i3;
/* 143:209 */     i3 = i1 << 1 | i1 >>> 31;
/* 144:210 */     k = (m << 5 | m >>> 27) + (n & i | (n ^ 0xFFFFFFFF) & j) + k + i3 + 1518500249;
/* 145:    */     
/* 146:212 */     n = n << 30 | n >>> 2;
/* 147:213 */     i1 = i17 ^ i12 ^ i6 ^ i4;
/* 148:214 */     i4 = i1 << 1 | i1 >>> 31;
/* 149:215 */     j = (k << 5 | k >>> 27) + (m & n | (m ^ 0xFFFFFFFF) & i) + j + i4 + 1518500249;
/* 150:    */     
/* 151:217 */     m = m << 30 | m >>> 2;
/* 152:218 */     i1 = i2 ^ i13 ^ i7 ^ i5;
/* 153:219 */     i5 = i1 << 1 | i1 >>> 31;
/* 154:220 */     i = (j << 5 | j >>> 27) + (k & m | (k ^ 0xFFFFFFFF) & n) + i + i5 + 1518500249;
/* 155:    */     
/* 156:222 */     k = k << 30 | k >>> 2;
/* 157:223 */     i1 = i3 ^ i14 ^ i8 ^ i6;
/* 158:224 */     i6 = i1 << 1 | i1 >>> 31;
/* 159:225 */     n = (i << 5 | i >>> 27) + (j ^ k ^ m) + n + i6 + 1859775393;
/* 160:    */     
/* 161:227 */     j = j << 30 | j >>> 2;
/* 162:228 */     i1 = i4 ^ i15 ^ i9 ^ i7;
/* 163:229 */     i7 = i1 << 1 | i1 >>> 31;
/* 164:230 */     m = (n << 5 | n >>> 27) + (i ^ j ^ k) + m + i7 + 1859775393;
/* 165:    */     
/* 166:232 */     i = i << 30 | i >>> 2;
/* 167:233 */     i1 = i5 ^ i16 ^ i10 ^ i8;
/* 168:234 */     i8 = i1 << 1 | i1 >>> 31;
/* 169:235 */     k = (m << 5 | m >>> 27) + (n ^ i ^ j) + k + i8 + 1859775393;
/* 170:    */     
/* 171:237 */     n = n << 30 | n >>> 2;
/* 172:238 */     i1 = i6 ^ i17 ^ i11 ^ i9;
/* 173:239 */     i9 = i1 << 1 | i1 >>> 31;
/* 174:240 */     j = (k << 5 | k >>> 27) + (m ^ n ^ i) + j + i9 + 1859775393;
/* 175:    */     
/* 176:242 */     m = m << 30 | m >>> 2;
/* 177:243 */     i1 = i7 ^ i2 ^ i12 ^ i10;
/* 178:244 */     i10 = i1 << 1 | i1 >>> 31;
/* 179:245 */     i = (j << 5 | j >>> 27) + (k ^ m ^ n) + i + i10 + 1859775393;
/* 180:    */     
/* 181:247 */     k = k << 30 | k >>> 2;
/* 182:248 */     i1 = i8 ^ i3 ^ i13 ^ i11;
/* 183:249 */     i11 = i1 << 1 | i1 >>> 31;
/* 184:250 */     n = (i << 5 | i >>> 27) + (j ^ k ^ m) + n + i11 + 1859775393;
/* 185:    */     
/* 186:252 */     j = j << 30 | j >>> 2;
/* 187:253 */     i1 = i9 ^ i4 ^ i14 ^ i12;
/* 188:254 */     i12 = i1 << 1 | i1 >>> 31;
/* 189:255 */     m = (n << 5 | n >>> 27) + (i ^ j ^ k) + m + i12 + 1859775393;
/* 190:    */     
/* 191:257 */     i = i << 30 | i >>> 2;
/* 192:258 */     i1 = i10 ^ i5 ^ i15 ^ i13;
/* 193:259 */     i13 = i1 << 1 | i1 >>> 31;
/* 194:260 */     k = (m << 5 | m >>> 27) + (n ^ i ^ j) + k + i13 + 1859775393;
/* 195:    */     
/* 196:262 */     n = n << 30 | n >>> 2;
/* 197:263 */     i1 = i11 ^ i6 ^ i16 ^ i14;
/* 198:264 */     i14 = i1 << 1 | i1 >>> 31;
/* 199:265 */     j = (k << 5 | k >>> 27) + (m ^ n ^ i) + j + i14 + 1859775393;
/* 200:    */     
/* 201:267 */     m = m << 30 | m >>> 2;
/* 202:268 */     i1 = i12 ^ i7 ^ i17 ^ i15;
/* 203:269 */     i15 = i1 << 1 | i1 >>> 31;
/* 204:270 */     i = (j << 5 | j >>> 27) + (k ^ m ^ n) + i + i15 + 1859775393;
/* 205:    */     
/* 206:272 */     k = k << 30 | k >>> 2;
/* 207:273 */     i1 = i13 ^ i8 ^ i2 ^ i16;
/* 208:274 */     i16 = i1 << 1 | i1 >>> 31;
/* 209:275 */     n = (i << 5 | i >>> 27) + (j ^ k ^ m) + n + i16 + 1859775393;
/* 210:    */     
/* 211:277 */     j = j << 30 | j >>> 2;
/* 212:278 */     i1 = i14 ^ i9 ^ i3 ^ i17;
/* 213:279 */     i17 = i1 << 1 | i1 >>> 31;
/* 214:280 */     m = (n << 5 | n >>> 27) + (i ^ j ^ k) + m + i17 + 1859775393;
/* 215:    */     
/* 216:282 */     i = i << 30 | i >>> 2;
/* 217:283 */     i1 = i15 ^ i10 ^ i4 ^ i2;
/* 218:284 */     i2 = i1 << 1 | i1 >>> 31;
/* 219:285 */     k = (m << 5 | m >>> 27) + (n ^ i ^ j) + k + i2 + 1859775393;
/* 220:    */     
/* 221:287 */     n = n << 30 | n >>> 2;
/* 222:288 */     i1 = i16 ^ i11 ^ i5 ^ i3;
/* 223:289 */     i3 = i1 << 1 | i1 >>> 31;
/* 224:290 */     j = (k << 5 | k >>> 27) + (m ^ n ^ i) + j + i3 + 1859775393;
/* 225:    */     
/* 226:292 */     m = m << 30 | m >>> 2;
/* 227:293 */     i1 = i17 ^ i12 ^ i6 ^ i4;
/* 228:294 */     i4 = i1 << 1 | i1 >>> 31;
/* 229:295 */     i = (j << 5 | j >>> 27) + (k ^ m ^ n) + i + i4 + 1859775393;
/* 230:    */     
/* 231:297 */     k = k << 30 | k >>> 2;
/* 232:298 */     i1 = i2 ^ i13 ^ i7 ^ i5;
/* 233:299 */     i5 = i1 << 1 | i1 >>> 31;
/* 234:300 */     n = (i << 5 | i >>> 27) + (j ^ k ^ m) + n + i5 + 1859775393;
/* 235:    */     
/* 236:302 */     j = j << 30 | j >>> 2;
/* 237:303 */     i1 = i3 ^ i14 ^ i8 ^ i6;
/* 238:304 */     i6 = i1 << 1 | i1 >>> 31;
/* 239:305 */     m = (n << 5 | n >>> 27) + (i ^ j ^ k) + m + i6 + 1859775393;
/* 240:    */     
/* 241:307 */     i = i << 30 | i >>> 2;
/* 242:308 */     i1 = i4 ^ i15 ^ i9 ^ i7;
/* 243:309 */     i7 = i1 << 1 | i1 >>> 31;
/* 244:310 */     k = (m << 5 | m >>> 27) + (n ^ i ^ j) + k + i7 + 1859775393;
/* 245:    */     
/* 246:312 */     n = n << 30 | n >>> 2;
/* 247:313 */     i1 = i5 ^ i16 ^ i10 ^ i8;
/* 248:314 */     i8 = i1 << 1 | i1 >>> 31;
/* 249:315 */     j = (k << 5 | k >>> 27) + (m ^ n ^ i) + j + i8 + 1859775393;
/* 250:    */     
/* 251:317 */     m = m << 30 | m >>> 2;
/* 252:318 */     i1 = i6 ^ i17 ^ i11 ^ i9;
/* 253:319 */     i9 = i1 << 1 | i1 >>> 31;
/* 254:320 */     i = (j << 5 | j >>> 27) + (k ^ m ^ n) + i + i9 + 1859775393;
/* 255:    */     
/* 256:322 */     k = k << 30 | k >>> 2;
/* 257:323 */     i1 = i7 ^ i2 ^ i12 ^ i10;
/* 258:324 */     i10 = i1 << 1 | i1 >>> 31;
/* 259:325 */     n = (i << 5 | i >>> 27) + (j & k | j & m | k & m) + n + i10 + -1894007588;
/* 260:    */     
/* 261:327 */     j = j << 30 | j >>> 2;
/* 262:328 */     i1 = i8 ^ i3 ^ i13 ^ i11;
/* 263:329 */     i11 = i1 << 1 | i1 >>> 31;
/* 264:330 */     m = (n << 5 | n >>> 27) + (i & j | i & k | j & k) + m + i11 + -1894007588;
/* 265:    */     
/* 266:332 */     i = i << 30 | i >>> 2;
/* 267:333 */     i1 = i9 ^ i4 ^ i14 ^ i12;
/* 268:334 */     i12 = i1 << 1 | i1 >>> 31;
/* 269:335 */     k = (m << 5 | m >>> 27) + (n & i | n & j | i & j) + k + i12 + -1894007588;
/* 270:    */     
/* 271:337 */     n = n << 30 | n >>> 2;
/* 272:338 */     i1 = i10 ^ i5 ^ i15 ^ i13;
/* 273:339 */     i13 = i1 << 1 | i1 >>> 31;
/* 274:340 */     j = (k << 5 | k >>> 27) + (m & n | m & i | n & i) + j + i13 + -1894007588;
/* 275:    */     
/* 276:342 */     m = m << 30 | m >>> 2;
/* 277:343 */     i1 = i11 ^ i6 ^ i16 ^ i14;
/* 278:344 */     i14 = i1 << 1 | i1 >>> 31;
/* 279:345 */     i = (j << 5 | j >>> 27) + (k & m | k & n | m & n) + i + i14 + -1894007588;
/* 280:    */     
/* 281:347 */     k = k << 30 | k >>> 2;
/* 282:348 */     i1 = i12 ^ i7 ^ i17 ^ i15;
/* 283:349 */     i15 = i1 << 1 | i1 >>> 31;
/* 284:350 */     n = (i << 5 | i >>> 27) + (j & k | j & m | k & m) + n + i15 + -1894007588;
/* 285:    */     
/* 286:352 */     j = j << 30 | j >>> 2;
/* 287:353 */     i1 = i13 ^ i8 ^ i2 ^ i16;
/* 288:354 */     i16 = i1 << 1 | i1 >>> 31;
/* 289:355 */     m = (n << 5 | n >>> 27) + (i & j | i & k | j & k) + m + i16 + -1894007588;
/* 290:    */     
/* 291:357 */     i = i << 30 | i >>> 2;
/* 292:358 */     i1 = i14 ^ i9 ^ i3 ^ i17;
/* 293:359 */     i17 = i1 << 1 | i1 >>> 31;
/* 294:360 */     k = (m << 5 | m >>> 27) + (n & i | n & j | i & j) + k + i17 + -1894007588;
/* 295:    */     
/* 296:362 */     n = n << 30 | n >>> 2;
/* 297:363 */     i1 = i15 ^ i10 ^ i4 ^ i2;
/* 298:364 */     i2 = i1 << 1 | i1 >>> 31;
/* 299:365 */     j = (k << 5 | k >>> 27) + (m & n | m & i | n & i) + j + i2 + -1894007588;
/* 300:    */     
/* 301:367 */     m = m << 30 | m >>> 2;
/* 302:368 */     i1 = i16 ^ i11 ^ i5 ^ i3;
/* 303:369 */     i3 = i1 << 1 | i1 >>> 31;
/* 304:370 */     i = (j << 5 | j >>> 27) + (k & m | k & n | m & n) + i + i3 + -1894007588;
/* 305:    */     
/* 306:372 */     k = k << 30 | k >>> 2;
/* 307:373 */     i1 = i17 ^ i12 ^ i6 ^ i4;
/* 308:374 */     i4 = i1 << 1 | i1 >>> 31;
/* 309:375 */     n = (i << 5 | i >>> 27) + (j & k | j & m | k & m) + n + i4 + -1894007588;
/* 310:    */     
/* 311:377 */     j = j << 30 | j >>> 2;
/* 312:378 */     i1 = i2 ^ i13 ^ i7 ^ i5;
/* 313:379 */     i5 = i1 << 1 | i1 >>> 31;
/* 314:380 */     m = (n << 5 | n >>> 27) + (i & j | i & k | j & k) + m + i5 + -1894007588;
/* 315:    */     
/* 316:382 */     i = i << 30 | i >>> 2;
/* 317:383 */     i1 = i3 ^ i14 ^ i8 ^ i6;
/* 318:384 */     i6 = i1 << 1 | i1 >>> 31;
/* 319:385 */     k = (m << 5 | m >>> 27) + (n & i | n & j | i & j) + k + i6 + -1894007588;
/* 320:    */     
/* 321:387 */     n = n << 30 | n >>> 2;
/* 322:388 */     i1 = i4 ^ i15 ^ i9 ^ i7;
/* 323:389 */     i7 = i1 << 1 | i1 >>> 31;
/* 324:390 */     j = (k << 5 | k >>> 27) + (m & n | m & i | n & i) + j + i7 + -1894007588;
/* 325:    */     
/* 326:392 */     m = m << 30 | m >>> 2;
/* 327:393 */     i1 = i5 ^ i16 ^ i10 ^ i8;
/* 328:394 */     i8 = i1 << 1 | i1 >>> 31;
/* 329:395 */     i = (j << 5 | j >>> 27) + (k & m | k & n | m & n) + i + i8 + -1894007588;
/* 330:    */     
/* 331:397 */     k = k << 30 | k >>> 2;
/* 332:398 */     i1 = i6 ^ i17 ^ i11 ^ i9;
/* 333:399 */     i9 = i1 << 1 | i1 >>> 31;
/* 334:400 */     n = (i << 5 | i >>> 27) + (j & k | j & m | k & m) + n + i9 + -1894007588;
/* 335:    */     
/* 336:402 */     j = j << 30 | j >>> 2;
/* 337:403 */     i1 = i7 ^ i2 ^ i12 ^ i10;
/* 338:404 */     i10 = i1 << 1 | i1 >>> 31;
/* 339:405 */     m = (n << 5 | n >>> 27) + (i & j | i & k | j & k) + m + i10 + -1894007588;
/* 340:    */     
/* 341:407 */     i = i << 30 | i >>> 2;
/* 342:408 */     i1 = i8 ^ i3 ^ i13 ^ i11;
/* 343:409 */     i11 = i1 << 1 | i1 >>> 31;
/* 344:410 */     k = (m << 5 | m >>> 27) + (n & i | n & j | i & j) + k + i11 + -1894007588;
/* 345:    */     
/* 346:412 */     n = n << 30 | n >>> 2;
/* 347:413 */     i1 = i9 ^ i4 ^ i14 ^ i12;
/* 348:414 */     i12 = i1 << 1 | i1 >>> 31;
/* 349:415 */     j = (k << 5 | k >>> 27) + (m & n | m & i | n & i) + j + i12 + -1894007588;
/* 350:    */     
/* 351:417 */     m = m << 30 | m >>> 2;
/* 352:418 */     i1 = i10 ^ i5 ^ i15 ^ i13;
/* 353:419 */     i13 = i1 << 1 | i1 >>> 31;
/* 354:420 */     i = (j << 5 | j >>> 27) + (k & m | k & n | m & n) + i + i13 + -1894007588;
/* 355:    */     
/* 356:422 */     k = k << 30 | k >>> 2;
/* 357:423 */     i1 = i11 ^ i6 ^ i16 ^ i14;
/* 358:424 */     i14 = i1 << 1 | i1 >>> 31;
/* 359:425 */     n = (i << 5 | i >>> 27) + (j ^ k ^ m) + n + i14 + -899497514;
/* 360:    */     
/* 361:427 */     j = j << 30 | j >>> 2;
/* 362:428 */     i1 = i12 ^ i7 ^ i17 ^ i15;
/* 363:429 */     i15 = i1 << 1 | i1 >>> 31;
/* 364:430 */     m = (n << 5 | n >>> 27) + (i ^ j ^ k) + m + i15 + -899497514;
/* 365:    */     
/* 366:432 */     i = i << 30 | i >>> 2;
/* 367:433 */     i1 = i13 ^ i8 ^ i2 ^ i16;
/* 368:434 */     i16 = i1 << 1 | i1 >>> 31;
/* 369:435 */     k = (m << 5 | m >>> 27) + (n ^ i ^ j) + k + i16 + -899497514;
/* 370:    */     
/* 371:437 */     n = n << 30 | n >>> 2;
/* 372:438 */     i1 = i14 ^ i9 ^ i3 ^ i17;
/* 373:439 */     i17 = i1 << 1 | i1 >>> 31;
/* 374:440 */     j = (k << 5 | k >>> 27) + (m ^ n ^ i) + j + i17 + -899497514;
/* 375:    */     
/* 376:442 */     m = m << 30 | m >>> 2;
/* 377:443 */     i1 = i15 ^ i10 ^ i4 ^ i2;
/* 378:444 */     i2 = i1 << 1 | i1 >>> 31;
/* 379:445 */     i = (j << 5 | j >>> 27) + (k ^ m ^ n) + i + i2 + -899497514;
/* 380:    */     
/* 381:447 */     k = k << 30 | k >>> 2;
/* 382:448 */     i1 = i16 ^ i11 ^ i5 ^ i3;
/* 383:449 */     i3 = i1 << 1 | i1 >>> 31;
/* 384:450 */     n = (i << 5 | i >>> 27) + (j ^ k ^ m) + n + i3 + -899497514;
/* 385:    */     
/* 386:452 */     j = j << 30 | j >>> 2;
/* 387:453 */     i1 = i17 ^ i12 ^ i6 ^ i4;
/* 388:454 */     i4 = i1 << 1 | i1 >>> 31;
/* 389:455 */     m = (n << 5 | n >>> 27) + (i ^ j ^ k) + m + i4 + -899497514;
/* 390:    */     
/* 391:457 */     i = i << 30 | i >>> 2;
/* 392:458 */     i1 = i2 ^ i13 ^ i7 ^ i5;
/* 393:459 */     i5 = i1 << 1 | i1 >>> 31;
/* 394:460 */     k = (m << 5 | m >>> 27) + (n ^ i ^ j) + k + i5 + -899497514;
/* 395:    */     
/* 396:462 */     n = n << 30 | n >>> 2;
/* 397:463 */     i1 = i3 ^ i14 ^ i8 ^ i6;
/* 398:464 */     i6 = i1 << 1 | i1 >>> 31;
/* 399:465 */     j = (k << 5 | k >>> 27) + (m ^ n ^ i) + j + i6 + -899497514;
/* 400:    */     
/* 401:467 */     m = m << 30 | m >>> 2;
/* 402:468 */     i1 = i4 ^ i15 ^ i9 ^ i7;
/* 403:469 */     i7 = i1 << 1 | i1 >>> 31;
/* 404:470 */     i = (j << 5 | j >>> 27) + (k ^ m ^ n) + i + i7 + -899497514;
/* 405:    */     
/* 406:472 */     k = k << 30 | k >>> 2;
/* 407:473 */     i1 = i5 ^ i16 ^ i10 ^ i8;
/* 408:474 */     i8 = i1 << 1 | i1 >>> 31;
/* 409:475 */     n = (i << 5 | i >>> 27) + (j ^ k ^ m) + n + i8 + -899497514;
/* 410:    */     
/* 411:477 */     j = j << 30 | j >>> 2;
/* 412:478 */     i1 = i6 ^ i17 ^ i11 ^ i9;
/* 413:479 */     i9 = i1 << 1 | i1 >>> 31;
/* 414:480 */     m = (n << 5 | n >>> 27) + (i ^ j ^ k) + m + i9 + -899497514;
/* 415:    */     
/* 416:482 */     i = i << 30 | i >>> 2;
/* 417:483 */     i1 = i7 ^ i2 ^ i12 ^ i10;
/* 418:484 */     i10 = i1 << 1 | i1 >>> 31;
/* 419:485 */     k = (m << 5 | m >>> 27) + (n ^ i ^ j) + k + i10 + -899497514;
/* 420:    */     
/* 421:487 */     n = n << 30 | n >>> 2;
/* 422:488 */     i1 = i8 ^ i3 ^ i13 ^ i11;
/* 423:489 */     i11 = i1 << 1 | i1 >>> 31;
/* 424:490 */     j = (k << 5 | k >>> 27) + (m ^ n ^ i) + j + i11 + -899497514;
/* 425:    */     
/* 426:492 */     m = m << 30 | m >>> 2;
/* 427:493 */     i1 = i9 ^ i4 ^ i14 ^ i12;
/* 428:494 */     i12 = i1 << 1 | i1 >>> 31;
/* 429:495 */     i = (j << 5 | j >>> 27) + (k ^ m ^ n) + i + i12 + -899497514;
/* 430:    */     
/* 431:497 */     k = k << 30 | k >>> 2;
/* 432:498 */     i1 = i10 ^ i5 ^ i15 ^ i13;
/* 433:499 */     i13 = i1 << 1 | i1 >>> 31;
/* 434:500 */     n = (i << 5 | i >>> 27) + (j ^ k ^ m) + n + i13 + -899497514;
/* 435:    */     
/* 436:502 */     j = j << 30 | j >>> 2;
/* 437:503 */     i1 = i11 ^ i6 ^ i16 ^ i14;
/* 438:504 */     i14 = i1 << 1 | i1 >>> 31;
/* 439:505 */     m = (n << 5 | n >>> 27) + (i ^ j ^ k) + m + i14 + -899497514;
/* 440:    */     
/* 441:507 */     i = i << 30 | i >>> 2;
/* 442:508 */     i1 = i12 ^ i7 ^ i17 ^ i15;
/* 443:509 */     i15 = i1 << 1 | i1 >>> 31;
/* 444:510 */     k = (m << 5 | m >>> 27) + (n ^ i ^ j) + k + i15 + -899497514;
/* 445:    */     
/* 446:512 */     n = n << 30 | n >>> 2;
/* 447:513 */     i1 = i13 ^ i8 ^ i2 ^ i16;
/* 448:514 */     i16 = i1 << 1 | i1 >>> 31;
/* 449:515 */     j = (k << 5 | k >>> 27) + (m ^ n ^ i) + j + i16 + -899497514;
/* 450:    */     
/* 451:517 */     m = m << 30 | m >>> 2;
/* 452:518 */     i1 = i14 ^ i9 ^ i3 ^ i17;
/* 453:519 */     i17 = i1 << 1 | i1 >>> 31;
/* 454:520 */     i = (j << 5 | j >>> 27) + (k ^ m ^ n) + i + i17 + -899497514;
/* 455:    */     
/* 456:522 */     k = k << 30 | k >>> 2;
/* 457:    */     
/* 458:524 */     this.currentVal[0] += i;
/* 459:525 */     this.currentVal[1] += j;
/* 460:526 */     this.currentVal[2] += k;
/* 461:527 */     this.currentVal[3] += m;
/* 462:528 */     this.currentVal[4] += n;
/* 463:    */   }
/* 464:    */   
/* 465:    */   public String toString()
/* 466:    */   {
/* 467:534 */     return "SHA-1";
/* 468:    */   }
/* 469:    */ }


/* Location:           E:\java decompi;er\burst.jar
 * Qualified Name:     fr.cryptohash.SHA1
 * JD-Core Version:    0.7.1
 */