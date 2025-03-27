//const admin = require("firebase-admin");
//const serviceAccount = require("./src/main/resources/firebase-service-account.json");
//
//admin.initializeApp({
//  credential: admin.credential.cert(serviceAccount)
//});
//
//async function createUser(email, password, displayName, phoneNumber) {
//  try {
//    const userRecord = await admin.auth().createUser({
//      email,
//      password,
//      displayName,
//      phoneNumber
//    });
//    console.log("User created successfully:", userRecord.uid);
//  } catch (error) {
//    if (error.code === "auth/email-already-exists") {
//      console.log("Email already in use. Fetching existing user...");
//      const userRecord = await admin.auth().getUserByEmail(email);
//      console.log("User exists:", userRecord.uid);
//    } else {
//      console.error("Error creating user:", error);
//    }
//  }
//}
//
//// Gantilah dengan email, password, dan nama pengguna yang ingin diuji
//createUser("testingdong@gmail.com", "password123", "Virtu Wear", "+628123456789");


//const admin = require("firebase-admin");
//const serviceAccount = require("./src/main/resources/firebase-service-account.json");
//
//admin.initializeApp({
//  credential: admin.credential.cert(serviceAccount)
//});
//
//// Gantilah email dengan yang sudah kamu daftarkan
//const email = "testingdong@gmail.com";
//const password = "password123";
//
//async function getUserToken(email) {
//  try {
//    // Ambil data user berdasarkan email
//    const userRecord = await admin.auth().getUserByEmail(email);
//
//    // Generate custom token berdasarkan UID
//    const customToken = await admin.auth().createCustomToken(userRecord.uid);
//    console.log("Custom Token:", customToken);
//  } catch (error) {
//    console.error("Error getting token:", error);
//  }
//}
//
//getUserToken(email);
