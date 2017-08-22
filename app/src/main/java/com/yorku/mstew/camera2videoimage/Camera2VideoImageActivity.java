package com.yorku.mstew.camera2videoimage;

import android.Manifest;
import android.animation.Animator;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Entity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;





import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.FaceDetector;

import android.graphics.Paint;
import android.app.Fragment;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.hardware.Sensor;
import android.hardware.camera2.params.BlackLevelPattern;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.DngCreator;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.ColorSpaceTransform;
import android.hardware.camera2.params.Face;
import android.hardware.camera2.params.RggbChannelVector;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.CamcorderProfile;
import android.media.FaceDetector;
import android.media.Image;
import android.media.ImageReader;
import android.media.MediaRecorder;
import android.media.audiofx.BassBoost;
import android.media.audiofx.Visualizer;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.telecom.VideoProfile;
import android.text.Editable;
import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
import android.transition.Scene;
import android.util.Log;
import android.util.Range;
import android.util.Rational;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.Utils;

import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.core.MatOfInt;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import static org.opencv.core.Core.absdiff;
 import static org.opencv.core.CvType.CV_16SC1;
import static org.opencv.core.CvType.CV_16U;
import static org.opencv.core.CvType.CV_16UC1;
 import static org.opencv.core.CvType.CV_8SC1;
 import static org.opencv.core.CvType.CV_8U;
import static org.opencv.imgcodecs.Imgcodecs.CV_IMWRITE_PNG_COMPRESSION;
import static org.opencv.imgcodecs.Imgcodecs.imwrite;
import static org.opencv.imgproc.Imgproc.COLOR_BGR2BGR555;
import static org.opencv.imgproc.Imgproc.cvtColor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.Scanner;
import java.util.zip.Inflater;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import static android.R.attr.bitmap;
import static android.R.attr.height;
import static android.R.attr.width;
import static android.R.attr.x;
import static android.R.attr.y;
import static android.hardware.camera2.CameraMetadata.CONTROL_AE_MODE_ON_ALWAYS_FLASH;
import static android.hardware.camera2.CameraMetadata.CONTROL_AE_MODE_ON_AUTO_FLASH;
import static android.hardware.camera2.CameraMetadata.CONTROL_AF_MODE_AUTO;
import static android.hardware.camera2.CameraMetadata.CONTROL_AWB_MODE_AUTO;
import static android.hardware.camera2.CameraMetadata.CONTROL_AWB_MODE_CLOUDY_DAYLIGHT;
import static android.hardware.camera2.CameraMetadata.CONTROL_AWB_MODE_DAYLIGHT;
import static android.hardware.camera2.CameraMetadata.CONTROL_AWB_MODE_FLUORESCENT;
import static android.hardware.camera2.CameraMetadata.CONTROL_AWB_MODE_INCANDESCENT;
import static android.hardware.camera2.CameraMetadata.CONTROL_AWB_MODE_SHADE;
import static android.hardware.camera2.CameraMetadata.CONTROL_AWB_MODE_TWILIGHT;
import static android.hardware.camera2.CameraMetadata.CONTROL_AWB_MODE_WARM_FLUORESCENT;
import static android.hardware.camera2.CameraMetadata.CONTROL_MODE_AUTO;
import static android.hardware.camera2.CameraMetadata.CONTROL_MODE_USE_SCENE_MODE;
import static android.hardware.camera2.CameraMetadata.CONTROL_SCENE_MODE_ACTION;
import static android.hardware.camera2.CameraMetadata.CONTROL_SCENE_MODE_BARCODE;
import static android.hardware.camera2.CameraMetadata.CONTROL_SCENE_MODE_BEACH;
import static android.hardware.camera2.CameraMetadata.CONTROL_SCENE_MODE_CANDLELIGHT;
import static android.hardware.camera2.CameraMetadata.CONTROL_SCENE_MODE_DISABLED;
import static android.hardware.camera2.CameraMetadata.CONTROL_SCENE_MODE_FACE_PRIORITY;
import static android.hardware.camera2.CameraMetadata.CONTROL_SCENE_MODE_FIREWORKS;
import static android.hardware.camera2.CameraMetadata.CONTROL_SCENE_MODE_HDR;
import static android.hardware.camera2.CameraMetadata.CONTROL_SCENE_MODE_LANDSCAPE;
import static android.hardware.camera2.CameraMetadata.CONTROL_SCENE_MODE_NIGHT;
import static android.hardware.camera2.CameraMetadata.CONTROL_SCENE_MODE_NIGHT_PORTRAIT;
import static android.hardware.camera2.CameraMetadata.CONTROL_SCENE_MODE_PARTY;
import static android.hardware.camera2.CameraMetadata.CONTROL_SCENE_MODE_PORTRAIT;
import static android.hardware.camera2.CameraMetadata.CONTROL_SCENE_MODE_SNOW;
import static android.hardware.camera2.CameraMetadata.CONTROL_SCENE_MODE_SPORTS;
import static android.hardware.camera2.CameraMetadata.CONTROL_SCENE_MODE_STEADYPHOTO;
import static android.hardware.camera2.CameraMetadata.CONTROL_SCENE_MODE_SUNSET;
import static android.hardware.camera2.CameraMetadata.CONTROL_SCENE_MODE_THEATRE;
import static android.hardware.camera2.CameraMetadata.FLASH_MODE_OFF;
import static android.hardware.camera2.CameraMetadata.FLASH_MODE_SINGLE;
import static android.hardware.camera2.CameraMetadata.FLASH_MODE_TORCH;
import static android.hardware.camera2.CameraMetadata.FLASH_STATE_UNAVAILABLE;
import static android.view.OrientationEventListener.ORIENTATION_UNKNOWN;
import static com.yorku.mstew.camera2videoimage.R.menu.advancedsettings;
import static com.yorku.mstew.camera2videoimage.R.menu.bottom_menu;
import static com.yorku.mstew.camera2videoimage.R.menu.popup_menu;
import static com.yorku.mstew.camera2videoimage.R.xml.resolution_xml;
import static java.lang.StrictMath.max;
import static java.lang.StrictMath.toIntExact;


import Jama.Matrix;


@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class Camera2VideoImageActivity extends Activity implements SensorEventListener {
    private SensorManager sm;
    private Button mModebutton;
    private int ISOvalue = 100;
    private int progressValue;
    private EditText mTextSeekBar;
    boolean TestBoolean=false;
    Size mSensorInfoActiveArraySize;
    private int mRawImageFormat=ImageFormat.RAW_SENSOR;
    private EditText mMinimumShutterSpeed;
    private EditText mMaximumShutterSpeed;
    private Button mAutobutton;
    private EditText mISOtext;
    public boolean mIsAuto2 = false;
    private int AutoNumber = 0;
    private boolean menuonline = false;
    private ImageButton mCloseALLbutton;
    private Button mShutterAuto;
    boolean ShutterAutoon = false;
    private String ShutterSpeed2String;
    private String ShutterSpeed1String;
    private static Uri mRequestingAppUri;
    ByteBuffer Bytebufferplane1;
    private SeekBar mSeekBar2;
    private ImageButton mFlipCamera;
    private Boolean FlipNumberBoolean = false;
    private int FlipNumber;
    private TextView mCameraInfoTextView;
    private TextView mCameraInfoTextView2;
    private TextView loadingtext;
    private TextView mCameraInfoTextView3;
    private TextView mCameraInfoTextView4;
    private TextView mCameraInfoTextView5;
    private SeekBar mISOseekbar;
    private int ISOprogressValue;
    private int ISOseekProgress;
    private boolean ISOinputboolean = false;
    private int mWBMode = CONTROL_AWB_MODE_AUTO;
    private Sound sound;
    private boolean mColorCorrectionMode=false;
    boolean WhiteBalanceCloudyDaylightBoolean = false;
    boolean WhiteBalanceDaylightBoolean = false;
    boolean WhiteBalanceFluorescentBoolean = false;
    boolean WhiteBalanceShadeBoolean = false;
    boolean WhiteBalanceTwilightBoolean = false;
    boolean WhiteBalanceWarmFluorescentBoolean = false;
    boolean WhiteBalanceIncandenscentBoolean = false;
    boolean WhiteBalanceAutoBoolean = true;
    Size[] previewSizes;
    private byte JPEGQuality=85;
    private boolean previewinit=true;
    private boolean AdjustWhiteBalanceonRawCapture=true;
    float eyedistance1=0;
    FaceDetector facedetector1;
    int hheight=0;
    int wwidth=0;
    Size Size1;
    Boolean trackfacesbool=false;
    Face[] faces=null;


    int[][] RedPixelValues2;
    int[][] GreenPixelValues2;
    int[][] BluePixelValues2;

    public int Colorfilter;


    private int mSceneMode = CONTROL_SCENE_MODE_FACE_PRIORITY;
    private int mAFMode = CONTROL_AF_MODE_AUTO;
    private EditText mISOEditText;
    private TextView mISOEditTextView;
    private EditText mShutterSpeedEditText;
    private TextView mShutterSpeedEditTextView;
    private EditText mShutterSpeedEditText2;
    private TextView mShutterSpeedEditTextView2;
    private SeekBar mChangeFocusSeekBar;
    private SeekBar ExposureCompensationSeekBar;
    private float ExposureCompensationIntegerProgress=0;
    private boolean ExposureCompensationSeekBarboolean;
    private boolean ExportasRGBasTextboolean=false;
    private boolean ExportAsRGGBasTextboolean=false;
    private int count2=0;
    public static boolean SettingresolutionChanged=false;
    private Mat mMat2;
    private Mat mMat;
    private Mat mMat3;
    private Mat tempMat;
    private Mat finalMat;
    private Mat sixtyFours;
    private int pixel;

    private LinearLayout mManualFocusLayout;
    private double mFocusDistance = 20;
    private float mFocusDistanceMem = 20;
    private double getmFocusDistanceMem = 20;
    boolean manualFocusEnableIsChecked = false;
    boolean mBurstOn = false;
    private int mBurstNumber = 0;
    private int ChronoCount = 0;
    private EditText mPhotoBurstText;
    private EditText mManualFocusInput;
    private EditText mPhotoBurstLimitText;
    private int mPhotoTimeLimitNumber = 1;
    private int mVideoTimeLimitNumber=1;
    private int SecondStep = 5;
    private int PhotoBurstTimeStop;
    private EditText mVideoTimelapse;
    private int VideoTimelapsSecondStep = 2;
    private ImageButton mFlashButtonOnOff;
    private int mFlashMode = 0;
    private boolean lockFocusEnableIsChecked = false;
    private boolean BooleanOpticalStabilizationOn = true;
    private int mTotalRotation;
    double[][] cArray=null;
    boolean RefreshBoolean=false;
    private String pixelData="";

    private TextView mTimeInterval;
    private int AutoLocks = 0;
    private int mCameraEffect = 0;
    private long mCurrentSSvalue = 500000000;
    private float mCurrentAperatureValue;
    private int CurrentJPEGQuality;

    int redPixelData;
    int bluePixelData;
    int greePixelData;
    int AverageredPixelData = 0;
    int AveragebluePixelData = 0;
    int AveragegreenPixelData = 0;
    int TotalRedPixelData;
    int TotalBluePixelData;
    int TotalGreenPixelData;
    private boolean blacklightSubtractionIsEnabled=true;

    private boolean isAdjustingWB2 = false;
    private boolean isAdjustingWB = false;
    public static ArrayList<Size> arraylist=new ArrayList<Size>();
    public static boolean arraylistcall=true;
    String SensorinfoColorfiltering="";



    private int imageWidth=0;
    private int imageHeight=0;
    private int rawWidth=25;
    private int rawHeight=25;



    private int[][] totalResult;
    private int[] totalResult1D;

    private ColorSpaceTransform mCurrentSensorColorTranform;

    private int mCurrentAutoFocus;
    private Integer afStateRealTime;
    private int mNumberofFaces;
    private int mNumberofFaces2;
    private int mCurrentISOValue = 200;
    private double mCurrentFocusDistance = 1;
    private boolean starttrackingbool=false;
    private float mMinFocusDistance;
    private float mMaxFocusDistance = 2;
    private TextView mFocusTextView;
    private boolean supports_face_detection_mode_simple;
    private boolean isSupports_face_detection_mode_full;
    TextView ExposureCompensationtextview;
    private FaceDetector FaceDetector;
    private String OFFtext = "";
    private String SIMPLEtext = "";
    private String FULLtext = "";
    private TextView mInfoTextView;
    private boolean mRawImageCaptureon = false;
    private boolean afstateBoolean = false;
    private CheckBox mRawCheckBox;
    private boolean UnlockFocusSpecialBooleanCaptureon = true;
    private boolean AutoWhiteBalancelockBoolean = false;
    private boolean SpotLockedWhiteBalanceBoolean = false;
    private boolean AverageSpotLockWhiteBalanceBoolean = false;
    public static boolean Intentinit=true;
    //private boolean Refreshinit=true;
    private static int mCurrentWidth=0;
    private static int mCurrentHeight=0;
    private boolean JPEGCaptureOn=true;
    int DenominatorStep=1;
    private int counterr;
    private int PipelineEditorNumber=0;


    private boolean CustomeWhiteBalanceBoolean = false;
    private RggbChannelVector rggbChannelVector;
    private ColorSpaceTransform ColorCorrectionTransform;
    EditText mWhitebalance1;
    EditText mWhitebalance2;
    EditText mWhitebalance3;
    EditText mWhitebalance4;
    double RggbChannelBlue=0;
    double RggbChannelG_even=0;
    double RggbChannelG_odd=0;
    double RggbChsnnelR=0;
    int ColorSpaceRed1;
    int ColorSpaceRed2;
    int ColorSpaceRed3;
    int ColorSpaceBlue1;
    int ColorSpaceBlue2;
    int ColorSpaceBlue3;
    int ColorSpaceGreen1;
    int ColorSpaceGreen2;
    int ColorSpaceGreen3;
    int temp = 0;
    int temp2 = 0;
    Animation loadingAnimation;
    private int mChronoTick=0;
    private int mRecordChronoTick=0;
    private int RecordTimeLimit;
    private int HotPixelMode=0;
    EditText mColorSpaceText1;
    EditText mColorSpaceText2;
    EditText mColorSpaceText3;
    EditText mColorSpaceText4;
    EditText mColorSpaceText5;
    EditText mColorSpaceText6;
    EditText mColorSpaceText7;
    EditText mColorSpaceText8;
    EditText mColorSpaceText9;
    boolean ColorSpaceInputBoolean = false;
    boolean WB_RAWTouchEnabled = false;
    boolean ForwardMatrixInputBoolean = false;
    boolean SensorColorTransformInputBoolean = false;
    Bitmap WhiteBalanceBallInspector;
    boolean ifOnCreate=false;
    boolean Capture_JPEG=true;
    //android.media.FaceDetector.Face[] face3;
    byte[] byteArray;
    boolean isItOka = true;
    float BallInspectorx, BallInspectory;
    float alphafloat = (float) 0;
    private int height;
    private int width;
    SurfaceHolder holder;
    ImageButton MovementButtonn;
    ImageView loadingemblem1;
    public CameraCharacteristics mCameraCharacteristics;
    int PatternTestint;
    boolean MovementButtonnBoolen = true;
    boolean ControlAWBmodecloudydaylightavailableboolean = false;
    boolean ControlAWBmodedaylightavailableboolean = false;
    boolean ControlAWBmodefluorescentavailableboolean = false;
    boolean ControlAWBmodeincandescentavailableboolean = false;
    boolean ControlAWBmodeshadeavailableboolean = false;
    boolean ControlAWBmodewarmfluorescentavailableboolean = false;
    boolean ControlAWBmodetwilightavailableboolean = false;
    boolean WBrunOnce = true;
    boolean CaptureAveragepixelCountBooleanOn = false;
    boolean ConvertRAWtoPNG=false;
    public static int MaxRawValueOutput;
    int ToneMapMode=1;
    TextView pngcapturenote;

    Button readButton;
    String scannedfilestring;
     boolean ShowRealTimeInfoboolean=false;
    int FrameRate=30;
    int BitEncodingRate=8000000;
    String NoiseReductionModeString="";
    private static final Rational ONE_R=new Rational(1,1);
    private static final Rational ZERO_R=Rational.ZERO;
    String CIEXYZvaluesString=null;
    boolean ShowCIEXYZValuesBoolean=true;
    byte[] bytes;
    boolean cameraReady=false;
    int NoiseReductionModesint=1;
    public static int[] NoiseReductionModes;
    public static int[] TestPatternModes;
    public static int [] EdgeModesAvailable;
    public  static int[] HotPixelModes;
    int EdgeMode=1;
    int AntiBandingModeint=3;
    boolean readRawonTap=false;
    boolean loadingalphaboolinit=true;

    public static boolean NoiseReductionModesinit=true;

    String s = "";
    String sTemp;
    float totalR = 0f;
    float totalG = 0f;
    float totalB = 0f;
    int[][] RedMatrix2;
    int[][] GreenMatrix2;
    int [][] BlueMatrix2;
    int averageR;
    int averageG;
    int averageB;
    private final int BAYERHEIGHT = 64;
    private final int BAYERWIDTH = 128;
    private double intervalTime=0;
    private int[][] pixelValues;
    private boolean wbThreadisEnabled = false;
    private static float mVectorR = 1.0f;
    private static float mVectorG_EVEN = 1.0f;
    private static float mVectorG_ODD = 1.0f;
    private static float mVectorB = 1.0f;
    private float leftface;
    private float rightface;
    private float bottomface;
    private float topface;

    private boolean ChangeWhiteBalanceSpotRawOn = false;
    private ImageReader mRawImageReader;
    private ImageReader mImageReader;
    int TempVideoTimeLimit;
    private static int mDeviceOrientation;
    public static final int UPSIDE_DOWN = 3;
    public static final int LANDSCAPE_RIGHT = 4;
    public static final int PORTRAIT = 1;
    public static final int LANDSCAPE_LEFT = 2;
    public int mOrientationDeg; //last rotation in degrees
    public int mOrientationRounded; //last orientation int from above
    private static final int _DATA_X = 0;
    private static final int _DATA_Y = 1;
    private static final int _DATA_Z = 2;
    private int ORIENTATION_UNKNOWN = -1;
    private int tempOrientRounded = -1;
    boolean CapturePngBoolean=false;
    FileOutputStream output;
    File txtfolder;
    File PNGRAWfolder=null;
    public static int [] AvailableTonemapModes;
    View alphaview;
    boolean loadingalphabool=false;



    Rational[] SensorColorTransform1Values;
    double[] SensorColorTransform1DoubleValues=new double[9];
    double[][] SensorColorTranform1Array;

    Rational[] SensorColorTransform2Values;
    double[] SensorColorTransform2DoubleValues=new double[9];
    double[][] SensorColorTranform2Array;

    Rational[] ForwardMatrix1Values;
    double[] ForwardMatrix1DoubleValues=new double[9];
    double[][] ForwardMatrix1Array;

    Rational[] ForwardMatrix2Values;
    double[] ForwardMatrix2DoubleValues=new double[9];
    double[][] ForwardMatrix2Array;

    Rational[] SensorCalibrationTransform1Values;
    double[] SensorCalibrationTransform1DoubleValues=new double[9];
    double[][]SensorCalibrationTransform1Array;

    Rational[] SensorCalibrationTransform2Values;
    double[] SensorCalibrationTransform2DoubleValues=new double[9];
    double[][]SensorCalibrationTransform2Array;
    SharedPreferences sharedprefs1;
    //private Mat finalMat;
    private Mat s1RawImage;
    private Mat s2BlackLightSubration;
    private Mat s3LeensCorrection;
    private Mat s4NoiseReduction;
    private Mat s5WhiteBalancing;
    private Mat s6ColorSpace;
    ImageButton pngfromRawImageButton;



    Jama.Matrix SensorColorTransform1Matrix;
    Jama.Matrix SensorColorTransform1MatrixInverse;
    Jama.Matrix SensorColorTransform2Matrix;
    Jama.Matrix SensorColorTransform2MatrixInverse;
    Jama.Matrix ForwardMatrix1;
    Jama.Matrix ForwardMatrix1Inverse;
    Jama.Matrix ForwardMatrix2;
    Jama.Matrix ForwardMatrix2Inverse;
    Jama.Matrix SensorCalibrationTransform1Matrix;
    Jama.Matrix SensorCalibrationTransform1MatrixInverse;
    Jama.Matrix SensorCalibrationTransform2Matrix;
    Jama.Matrix SensorCalibrationTransform2MatrixInverse;
    Jama.Matrix RGGBChannelMatrix;

    









    @Override
    public void onSensorChanged(SensorEvent event)
    {


        Log.d(TAG, "Sensor Changed");
        float[] values = event.values;
        int orientation = ORIENTATION_UNKNOWN;
        float X = -values[_DATA_X];
        float Y = -values[_DATA_Y];
        float Z = -values[_DATA_Z];
        float magnitude = X*X + Y*Y;
        // Don't trust the angle if the magnitude is small compared to the y value
        if (magnitude * 4 >= Z*Z) {
            float OneEightyOverPi = 57.29577957855f;
            float angle = (float)Math.atan2(-Y, X) * OneEightyOverPi;
            orientation = 90 - (int)Math.round(angle);
            // normalize to 0 - 359 range
            while (orientation >= 360) {
                orientation -= 360;
            }
            while (orientation < 0) {
                orientation += 360;
            }
        }
        //^^ this code deals with orientation based on your X,Y,Z values
        //now we must figure out which orientation based on the degrees

        if (orientation != mOrientationDeg)
        {
            mOrientationDeg = orientation;
            //figure out actual orientation
            if(orientation == -1){//basically flat

            }
            else if(orientation <= 60 || orientation > 300){//round to 0
                tempOrientRounded = PORTRAIT;//portrait
            }
            else if(orientation > 45 && orientation <= 135){//round to 90
                tempOrientRounded = LANDSCAPE_LEFT; //lsleft
            }
            else if(orientation > 135 && orientation <= 225){//round to 180
                tempOrientRounded = UPSIDE_DOWN; //upside down
            }
            else if(orientation > 225 && orientation <= 315){//round to 270
                tempOrientRounded = LANDSCAPE_RIGHT;//lsright
            }

        }

        if(mOrientationRounded != tempOrientRounded){
            if (tempOrientRounded == LANDSCAPE_LEFT) {
                //Toast.makeText(this, tempOrientRounded + " Landscape Left", Toast.LENGTH_SHORT).show();
                mDeviceOrientation = 90;
            } else if (tempOrientRounded == LANDSCAPE_RIGHT) {
                //Toast.makeText(this, tempOrientRounded + " Landscape Right", Toast.LENGTH_SHORT).show();
                mDeviceOrientation = 270;
            } else if (tempOrientRounded == UPSIDE_DOWN) {
                //Toast.makeText(this, tempOrientRounded + " Upside Down", Toast.LENGTH_SHORT).show();
                mDeviceOrientation = 180;
            } else if (tempOrientRounded == PORTRAIT) {
                //Toast.makeText(this, tempOrientRounded + " Portrait", Toast.LENGTH_SHORT).show();
                mDeviceOrientation = 0;
            }

            mOrientationRounded = tempOrientRounded;

        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub

    }


    //firstly we want to make the window sticky. We acheive this by making system flags
    //Making the window sticky

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView = getWindow().getDecorView();
        if (hasFocus) {
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    }

    //This is our attribute section.Note:this list will increase as we progress through the tutorial. We will create all members in this section:
    //private CameraDevice mCameraDevice;

    // private HandlerThread mBackgroundHandlerThread;
    //private Handler mBackgroundHandler;
    private Size mPreviewSize;



    //create surface texture listener
    private TextureView mTextureView;
    private TextureView.SurfaceTextureListener mSurfaceTextureListener = new TextureView.SurfaceTextureListener() {

        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            if(previewinit){
                Size1=new Size(height,width);

            }



            Toast.makeText(Camera2VideoImageActivity.this, width+""+height, Toast.LENGTH_SHORT).show();
            if(mCurrentHeight>0){
                setupCamera(mCurrentHeight,mCurrentWidth);
            }else{
                if(width>height){
                    setupCamera(height,width);
                }else{
                    setupCamera(width,height);
                }
            }

            connectCamera();

            // Toast.makeText(getApplicationContext(), "Texture is available", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {

        }
    };

    long ShutterSpeedValue;
    long xx2;

    @Override
    protected void onResume() {



        startBackgroundThread();
        if(!OpenCVLoader.initDebug()){
            Log.d(TAG,"Internal OpenCv library not found.Using OpenCV Manager for intialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0,this, mLoaderCallback);
        }else{
            Log.d(TAG,"OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
        //startBackgroundThread();

        if(sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size()!=0){
            Sensor s=sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            sm.registerListener(this,s,SensorManager.SENSOR_DELAY_NORMAL);
        }

        if (mTextureView.isAvailable()) {
            setupCamera(Size1.getWidth(), Size1.getHeight());

            //adjustAspectRatio(mTextureView.getWidth(),mTextureView.getHeight());
            connectCamera();
        } else {
            mTextureView.setSurfaceTextureListener(mSurfaceTextureListener);
            //connectCamera();
            //setupCamera(Size1.getWidth(),Size1.getHeight());

        }
        if(previewinit)
        {


        }else{
            RefreshScreen();
            connectCamera();
        }
        super.onResume();
    }




    //Creating the camera device
    private CameraDevice mCameraDevice;
    private CameraDevice.StateCallback mCameraDeviceStateCallback = new CameraDevice.StateCallback() {

        @Override
        public void onOpened(@NonNull CameraDevice camera) {
            mCameraDevice = camera;
            //Toast.makeText(getApplicationContext(), "Camera Connected", Toast.LENGTH_SHORT).show();

            if (mIsRecording) {
                try {
                    createVideoFileName();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                startRecord();
                mMediaRecorder.start();

                try {
                    mChronometer.setBase(SystemClock.elapsedRealtime());
                    mChronometer.setVisibility(View.VISIBLE);
                    mChronometer.start();
                    //new
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                //startPreview();
            }
            startPreview();
        }

        @Override
        public void onDisconnected(CameraDevice camera) {
            camera.close();
            mCameraDevice = null;
        }

        @Override
        public void onError(CameraDevice camera, int error) {
            camera.close();
            mCameraDevice = null;
        }

    };
    //Getting Camera Id
    private String mCameraId;

    StreamConfigurationMap map;
    private CameraCaptureSession mPreviewCaptureSession;
    private CameraCaptureSession.CaptureCallback mPreviewCaptureCallback = new
            CameraCaptureSession.CaptureCallback() {
                private void process(CaptureResult captureResult) {
                    //Integer mode = captureResult.get(CaptureResult.STATISTICS_FACE_DETECT_MODE);
                    //Face[] faces = captureResult.get(CaptureResult.STATISTICS_FACES);
                    //if (faces != null && mode != null) {
                        //Log.e("tag", "faces:"+ faces.length + ", mode" + mode);
                    //}
                    switch (mCaptureState) {
                        case STATE_PREVIEW:
                            //Do nothing
                            break;
                        case STATE_WAIT_LOCK:

                            mCaptureState = STATE_PREVIEW;
                            startStillCaptureRequest();

                            /*if(!manualFocusEnableIsChecked) {
                                if (!lockFocusEnableIsChecked) {
                                    //unLockFocus();
                                }
                            }
                            Integer afState = captureResult.get(CaptureResult.CONTROL_AF_STATE);
                            if (afState == CaptureResult.CONTROL_AF_STATE_FOCUSED_LOCKED) {
                                Toast.makeText(getApplicationContext(), "Autofocus locked", Toast.LENGTH_SHORT).show();
                            }
                            if (afState == CaptureResult.CONTROL_AF_STATE_NOT_FOCUSED_LOCKED) {
                                Toast.makeText(getApplicationContext(), "Autofocus not locked!", Toast.LENGTH_SHORT).show();
                            }*/

                            break;
                    }

                }

                @Override
                public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result)


                {
                    /*
                    mCurrentFocusDistance = result.get(CaptureResult.LENS_FOCUS_DISTANCE);
                    mCurrentISOValue = result.get(CaptureResult.SENSOR_SENSITIVITY);
                    mCurrentSSvalue = result.get(CaptureResult.SENSOR_EXPOSURE_TIME);
                    */



                    super.onCaptureCompleted(session, request, result);
                    //mCaptureResult = result;
                    process(result);

                    //

                }
            };
    /*private boolean hasPermissionsGranted(String[] permissions){
        for (String permission: permissions){
            if(ActivityCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED)
            {
                return false;
            }
        }
        return true;
    } */
    private static final String[] VIDEO_PERMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
    };

    private void setupCamera(int width, int height) {
       /* if(!hasPermissionsGranted(VIDEO_PERMISSIONS)){
            //requestVideoPermissions();
            return;
        } */

        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        mCurrentWidth=height;
        mCurrentHeight=width;
        cameraReady=true;
        try {

            mCameraId = cameraManager.getCameraIdList()[FlipNumber];
            CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(mCameraId);

            map = cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            //int deviceOrientation = getWindowManager().getDefaultDisplay().getRotation();
            mTotalRotation= sensorDeviceRotation(cameraCharacteristics,mDeviceOrientation);

            boolean swapRotation = (mTotalRotation== 90 || mTotalRotation == 270);
            int rotatedWidth = width;
            int rotatedHeight = height;
            if (swapRotation) {
                rotatedWidth = height;
                rotatedHeight = width;
                //Camera2VideoImageActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

            }

            mPreviewSize = chooseOptimalSize(map.getOutputSizes(SurfaceTexture.class), rotatedWidth, rotatedHeight);
            mVideoSize = chooseOptimalSize(map.getOutputSizes(MediaRecorder.class), rotatedWidth, rotatedHeight);
            mImageSize = chooseOptimalSize(map.getOutputSizes(ImageFormat.JPEG), rotatedWidth, rotatedHeight);
            mImageReader = ImageReader.newInstance(mImageSize.getWidth(), mImageSize.getHeight(), ImageFormat.JPEG, 1);
            mImageReader.setOnImageAvailableListener(mOnImageAvailableListener, mBackgroundHandler);


            //mPNGImageSize=chooseOptimalSize(map.getOutputSizes(ImageFormat.PNG),rotatedWidth,rotatedHeight);
            //mPNGImagheReader=ImageReader.newInstance(mPNGImageSize.getWidth(),mPNGImageSize.getHeight(),ImageFormat.P,1)


            mRawImageSize = chooseOptimalSize(map.getOutputSizes(mRawImageFormat), rotatedWidth, rotatedHeight);
            mRawImageReader = ImageReader.newInstance(mRawImageSize.getWidth(), mRawImageSize.getHeight(), mRawImageFormat, 1);
            mRawImageReader.setOnImageAvailableListener(mOnRawImageAvailableListener, mBackgroundHandler);


            mCameraCharacteristics = cameraCharacteristics;


            DenominatorStep=mCameraCharacteristics.get(mCameraCharacteristics.CONTROL_AE_COMPENSATION_STEP).getDenominator();

            ExposureCompensationSeekBar.setMax(((mCameraCharacteristics.get(mCameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE).getUpper())-(mCameraCharacteristics.get(mCameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE).getLower()))*DenominatorStep);
            ExposureCompensationSeekBar.setProgress(((mCameraCharacteristics.get(mCameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE).getUpper())-(mCameraCharacteristics.get(mCameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE).getLower()))*DenominatorStep/2);




            ColorSpaceTransform ColorSpaceTransformSensorColorTransform1=mCameraCharacteristics.get(mCameraCharacteristics.SENSOR_COLOR_TRANSFORM1);
            SensorColorTransform1Values=new Rational[9];
            ColorSpaceTransformSensorColorTransform1.copyElements(SensorColorTransform1Values,0);

            /* SensorColorTransorm1Array= new Rational[][]{{SensorColorTransform1Values[0], SensorColorTransform1Values[1], SensorColorTransform1Values[2]}, {SensorColorTransform1Values[3], SensorColorTransform1Values[4]
                    , SensorColorTransform1Values[5]}, {SensorColorTransform1Values[6], SensorColorTransform1Values[7], SensorColorTransform1Values[8]}};*/

            ColorSpaceTransform ColorSpaceTransformSensorColorTransform2=mCameraCharacteristics.get(mCameraCharacteristics.SENSOR_COLOR_TRANSFORM2);
            SensorColorTransform2Values=new Rational[9];
            ColorSpaceTransformSensorColorTransform2.copyElements(SensorColorTransform2Values,0);
            /*
            SensorColorTransorm2Array= new Rational[][]{{SensorColorTransform2Values[0], SensorColorTransform2Values[1], SensorColorTransform2Values[2]}, {SensorColorTransform2Values[3], SensorColorTransform2Values[4]
                    , SensorColorTransform2Values[5]}, {SensorColorTransform2Values[6], SensorColorTransform2Values[7], SensorColorTransform2Values[8]}};*/



            ColorSpaceTransform ColorSpaceTransformForwardMatrix1=mCameraCharacteristics.get(mCameraCharacteristics.SENSOR_FORWARD_MATRIX1);
            ForwardMatrix1Values=new Rational[9];
            ColorSpaceTransformForwardMatrix1.copyElements(ForwardMatrix1Values,0);

            ColorSpaceTransform ColorSpaceTransformForwardMatrix2=mCameraCharacteristics.get(mCameraCharacteristics.SENSOR_FORWARD_MATRIX2);
            ForwardMatrix2Values=new Rational[9];
            ColorSpaceTransformForwardMatrix2.copyElements(ForwardMatrix2Values,0);

            ColorSpaceTransform ColorSpaceTransformSensorCalibrationTransform1Values=mCameraCharacteristics.get(mCameraCharacteristics.SENSOR_CALIBRATION_TRANSFORM1);
            SensorCalibrationTransform1Values=new Rational[9];
            ColorSpaceTransformSensorCalibrationTransform1Values.copyElements(SensorCalibrationTransform1Values,0);

            ColorSpaceTransform ColorSpaceTransformSensorCalibrationTransform2Values=mCameraCharacteristics.get(mCameraCharacteristics.SENSOR_CALIBRATION_TRANSFORM2);
            SensorCalibrationTransform2Values=new Rational[9];
            ColorSpaceTransformSensorCalibrationTransform2Values.copyElements(SensorCalibrationTransform2Values,0);

            PipeDreams();
            
            return;
            //continue;
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void PipeDreams() {
        for(int i=0;i<SensorColorTransform1Values.length;i++){
            SensorColorTransform1DoubleValues[i]=
                    (Double.parseDouble(SensorColorTransform1Values[i].toString().split("/")[0])/Double.parseDouble(SensorColorTransform1Values[i].toString().split("/")[1]));
            SensorColorTransform2DoubleValues[i]=
                    (Double.parseDouble(SensorColorTransform2Values[i].toString().split("/")[0])/Double.parseDouble(SensorColorTransform2Values[i].toString().split("/")[1]));
            SensorCalibrationTransform1DoubleValues[i]=
                    (Double.parseDouble(SensorCalibrationTransform1Values[i].toString().split("/")[0])/Double.parseDouble(SensorCalibrationTransform1Values[i].toString().split("/")[1]));
            SensorCalibrationTransform2DoubleValues[i]=
                    (Double.parseDouble(SensorCalibrationTransform2Values[i].toString().split("/")[0])/Double.parseDouble(SensorCalibrationTransform2Values[i].toString().split("/")[1]));
            ForwardMatrix1DoubleValues[i]=
                    (Double.parseDouble(ForwardMatrix1Values[i].toString().split("/")[0])/Double.parseDouble(ForwardMatrix1Values[i].toString().split("/")[1]));
            ForwardMatrix2DoubleValues[i]=
                    (Double.parseDouble(ForwardMatrix2Values[i].toString().split("/")[0])/Double.parseDouble(ForwardMatrix2Values[i].toString().split("/")[1]));

        }
        for (int i = 0; i < mCameraCharacteristics.get(CameraCharacteristics.STATISTICS_INFO_AVAILABLE_FACE_DETECT_MODES).length; i++) {


            if (mCameraCharacteristics.get(CameraCharacteristics.STATISTICS_INFO_AVAILABLE_FACE_DETECT_MODES)[i] == 0) {
                OFFtext = "OFF";

            }
            if (mCameraCharacteristics.get(CameraCharacteristics.STATISTICS_INFO_AVAILABLE_FACE_DETECT_MODES)[i] == 1) {
                SIMPLEtext = "SIMPLE";
                supports_face_detection_mode_simple = true;
            }
            if (mCameraCharacteristics.get(CameraCharacteristics.STATISTICS_INFO_AVAILABLE_FACE_DETECT_MODES)[i] == 2) {
                FULLtext = "FULL";
                isSupports_face_detection_mode_full = true;
            }
            //String newText4 = oldTextView4 + "" + mCameraCharacteristics.get(CameraCharacteristics.STATISTICS_INFO_AVAILABLE_FACE_DETECT_MODES)[i]+ " , ";

        }

        RGGBChannelMatrix=new Matrix(new double[]{RggbChsnnelR,RggbChannelG_even,RggbChannelG_odd,RggbChannelBlue},1);
        SensorColorTranform1Array= new double[][]{{SensorColorTransform1DoubleValues[0],SensorColorTransform1DoubleValues[1],SensorColorTransform1DoubleValues[2]},{SensorColorTransform1DoubleValues[3],SensorColorTransform1DoubleValues[4],SensorColorTransform1DoubleValues[5]},{SensorColorTransform1DoubleValues[6],SensorColorTransform1DoubleValues[7],SensorColorTransform1DoubleValues[8]}};
              SensorColorTranform2Array= new double[][]{{SensorColorTransform2DoubleValues[0],SensorColorTransform2DoubleValues[1],SensorColorTransform2DoubleValues[2]},{SensorColorTransform2DoubleValues[3],SensorColorTransform2DoubleValues[4],SensorColorTransform2DoubleValues[5]},{SensorColorTransform2DoubleValues[6],SensorColorTransform2DoubleValues[7],SensorColorTransform2DoubleValues[8]}};
              SensorCalibrationTransform1Array=new double[][]{{SensorCalibrationTransform1DoubleValues[0],SensorCalibrationTransform1DoubleValues[1],SensorCalibrationTransform1DoubleValues[2]},{SensorCalibrationTransform1DoubleValues[3],SensorCalibrationTransform1DoubleValues[4],SensorCalibrationTransform1DoubleValues[5]},{SensorCalibrationTransform1DoubleValues[6],SensorCalibrationTransform1DoubleValues[7],SensorCalibrationTransform1DoubleValues[8]}};
              SensorCalibrationTransform2Array=new double[][]{{SensorCalibrationTransform2DoubleValues[0],SensorCalibrationTransform2DoubleValues[1],SensorCalibrationTransform2DoubleValues[2]},{SensorCalibrationTransform2DoubleValues[3],SensorCalibrationTransform2DoubleValues[4],SensorCalibrationTransform2DoubleValues[5]},{SensorCalibrationTransform2DoubleValues[6],SensorCalibrationTransform2DoubleValues[7],SensorCalibrationTransform2DoubleValues[8]}};
              ForwardMatrix1Array=new double[][]{{ForwardMatrix1DoubleValues[0],ForwardMatrix1DoubleValues[1],ForwardMatrix1DoubleValues[2]},{ForwardMatrix1DoubleValues[3],ForwardMatrix1DoubleValues[4],ForwardMatrix1DoubleValues[5]},{ForwardMatrix1DoubleValues[6],ForwardMatrix1DoubleValues[7],ForwardMatrix1DoubleValues[8]}};
              ForwardMatrix2Array=new double[][]{{ForwardMatrix2DoubleValues[0],ForwardMatrix2DoubleValues[1],ForwardMatrix2DoubleValues[2]},{ForwardMatrix2DoubleValues[3],ForwardMatrix2DoubleValues[4],ForwardMatrix2DoubleValues[5]},{ForwardMatrix2DoubleValues[6],ForwardMatrix2DoubleValues[7],ForwardMatrix2DoubleValues[8]}};
            SensorColorTransform1Matrix=new Matrix(SensorColorTranform1Array);
            SensorColorTransform2Matrix=new Matrix(SensorColorTranform2Array);
            SensorCalibrationTransform1Matrix=new Matrix(SensorCalibrationTransform1Array);
            SensorCalibrationTransform2Matrix=new Matrix(SensorCalibrationTransform2Array);
            ForwardMatrix1=new Matrix(ForwardMatrix1Array);
            ForwardMatrix2=new Matrix(ForwardMatrix2Array);
            SensorColorTransform1MatrixInverse=SensorColorTransform1Matrix.inverse();
            SensorColorTransform2MatrixInverse=SensorColorTransform2Matrix.inverse();
            ForwardMatrix1Inverse=ForwardMatrix1.inverse();
            ForwardMatrix2Inverse=ForwardMatrix2.inverse();
            SensorCalibrationTransform1MatrixInverse=SensorColorTransform1Matrix.inverse();
            SensorCalibrationTransform2MatrixInverse=SensorCalibrationTransform2Matrix.inverse();
        mSensorInfoActiveArraySize=mCameraCharacteristics.get(mCameraCharacteristics.SENSOR_INFO_PIXEL_ARRAY_SIZE);
        facedetector1=new FaceDetector(mTextureView.getWidth(),mTextureView.getHeight(),3);

        NoiseReductionModes=new int[(mCameraCharacteristics.get(mCameraCharacteristics.NOISE_REDUCTION_AVAILABLE_NOISE_REDUCTION_MODES)).length];
            TestPatternModes=new int[(mCameraCharacteristics.get(mCameraCharacteristics.SENSOR_AVAILABLE_TEST_PATTERN_MODES)).length];
            EdgeModesAvailable=new int[(mCameraCharacteristics.get(mCameraCharacteristics.EDGE_AVAILABLE_EDGE_MODES)).length];
        HotPixelModes=new int[(mCameraCharacteristics.get(mCameraCharacteristics.HOT_PIXEL_AVAILABLE_HOT_PIXEL_MODES)).length];
        AvailableTonemapModes=new int[mCameraCharacteristics.get(mCameraCharacteristics.TONEMAP_AVAILABLE_TONE_MAP_MODES).length];

            for (int j=0;j<(mCameraCharacteristics.get(mCameraCharacteristics.NOISE_REDUCTION_AVAILABLE_NOISE_REDUCTION_MODES)).length;j++){
                NoiseReductionModeString=NoiseReductionModeString+mCameraCharacteristics.get(mCameraCharacteristics.NOISE_REDUCTION_AVAILABLE_NOISE_REDUCTION_MODES)[j]+",";
                NoiseReductionModes[j]=mCameraCharacteristics.get(mCameraCharacteristics.NOISE_REDUCTION_AVAILABLE_NOISE_REDUCTION_MODES)[j];

            }
            for(int k=0;k<(mCameraCharacteristics.get(mCameraCharacteristics.SENSOR_AVAILABLE_TEST_PATTERN_MODES)).length;k++){
                TestPatternModes[k]=(mCameraCharacteristics.get(mCameraCharacteristics.SENSOR_AVAILABLE_TEST_PATTERN_MODES))[k];


            }
            for(int l=0;l<(mCameraCharacteristics.get(mCameraCharacteristics.EDGE_AVAILABLE_EDGE_MODES)).length;l++){
                EdgeModesAvailable[l]=(mCameraCharacteristics.get(mCameraCharacteristics.EDGE_AVAILABLE_EDGE_MODES))[l];
            }
            for (int m=0;m<(mCameraCharacteristics.get(mCameraCharacteristics.HOT_PIXEL_AVAILABLE_HOT_PIXEL_MODES).length);m++){
                HotPixelModes[m]=mCameraCharacteristics.get(mCameraCharacteristics.HOT_PIXEL_AVAILABLE_HOT_PIXEL_MODES)[m];

            }
            for (int n=0;n<(mCameraCharacteristics.get(mCameraCharacteristics.TONEMAP_AVAILABLE_TONE_MAP_MODES)).length;n++){
                AvailableTonemapModes[n]=mCameraCharacteristics.get(mCameraCharacteristics.TONEMAP_AVAILABLE_TONE_MAP_MODES)[n];
            }

        int SensorinfoColorFiltering=mCameraCharacteristics.get(mCameraCharacteristics.SENSOR_INFO_COLOR_FILTER_ARRANGEMENT);
        MaxRawValueOutput=mCameraCharacteristics.get(mCameraCharacteristics.SENSOR_INFO_WHITE_LEVEL);






        switch(SensorinfoColorFiltering){
            case 0:
                SensorinfoColorfiltering="RGGB";
                break;
            case 1:
                SensorinfoColorfiltering="GRBG";
                break;
            case 2:
                SensorinfoColorfiltering="GBRG";
                break;
            case 3:
                SensorinfoColorfiltering="BGGR";
                break;
            case 4:
                SensorinfoColorfiltering="RGB";
                break;


        }
        if(ifOnCreate)
        {
            ifOnCreate=false;
            SharedPreferences.Editor spe=sharedprefs1.edit();
            spe.putBoolean("onCreatePref",false);
            spe.commit();
            Intent i=new Intent(this,ViewPagerMainActivity.class);
            startActivity(i);
        }





    }

    private static final int REQUEST_CAMERA_PERMISSION_RESULT = 0;

    private void connectCamera() {

        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)==PackageManager.PERMISSION_GRANTED) {

                    cameraManager.openCamera(mCameraId, mCameraDeviceStateCallback, mBackgroundHandler);

                }else{
                        if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                            Toast.makeText(this, "App requires access to camera", Toast.LENGTH_SHORT).show();


                        }
                    requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_CAMERA_PERMISSION_RESULT);


                }



                } else {


                cameraManager.openCamera(mCameraId, mCameraDeviceStateCallback, mBackgroundHandler);
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void adjustWhiteBalanceOnTouch() {
        try {
            WhiteBalanceAutoBoolean=false;
            Toast.makeText(this, "Part1", Toast.LENGTH_SHORT).show();

            mCaptureRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
            Toast.makeText(this, "Part2", Toast.LENGTH_SHORT).show();
            mCaptureRequestBuilder.addTarget(mRawImageReader.getSurface());

            CameraCaptureSession.CaptureCallback stillCaptureCallback = new CameraCaptureSession.CaptureCallback() {
                @Override
                public void onCaptureStarted(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, long timestamp, long frameNumber) {
                    super.onCaptureStarted(session, request, timestamp, frameNumber);
                }

                @Override
                public void onCaptureCompleted(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull TotalCaptureResult result) {
                    super.onCaptureCompleted(session, request, result);
                }
            };
            mPreviewCaptureSession.capture(mCaptureRequestBuilder.build(), null, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        if(!ConvertRAWtoPNG && readRawonTap){

                    loadingtext.setVisibility(View.INVISIBLE);
                    loadingemblem1.clearAnimation();
                    loadingAnimation.cancel();
                    loadingAnimation.reset();
                    loadingemblem1.setVisibility(View.INVISIBLE);

                    alphaview.setAlpha(0f);

        }
        startPreview();





    }

    //close the camera
    private void closeCamera() {
        if (mCameraDevice != null) {
            mCameraDevice.close();
            mCameraDevice = null;
        }
    }

    //Creating a background thread
    private HandlerThread mBackgroundHandlerThread;

    private Handler mBackgroundHandler;

    private void startBackgroundThread() {
        mBackgroundHandlerThread = new HandlerThread("Cam2VideoImage");
        mBackgroundHandlerThread.start();
        mBackgroundHandler = new Handler(mBackgroundHandlerThread.getLooper());

    }

    private void stopBackgroundThread() {
        mBackgroundHandlerThread.quitSafely();
        try {
            mBackgroundHandlerThread.join();
            mBackgroundHandlerThread = null;
            mBackgroundHandler = null;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Adjusting orientation for calculating preview size
    private static SparseIntArray ORIENTATIONS = new SparseIntArray();

    static {
        ORIENTATIONS.append(Surface.ROTATION_0, 0);
        ORIENTATIONS.append(Surface.ROTATION_90, 90);
        ORIENTATIONS.append(Surface.ROTATION_180, 180);
        ORIENTATIONS.append(Surface.ROTATION_270, 270);


    }

     private static int sensorDeviceRotation(CameraCharacteristics cameraCharacteristics, int deviceOrientation) {
        int sensorOrientation = cameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);
        //deviceOrientation = ORIENTATIONS.get(deviceOrientation);
        return (sensorOrientation + mDeviceOrientation + 180) % 360;

    }


    //setting preview size dimensions
    private static class CompareSizeByArea implements Comparator<Size> {
        @Override
        public int compare(Size lhs, Size rhs) {
            return Long.signum((long) lhs.getWidth() * lhs.getHeight() -
                    (long) rhs.getWidth() * rhs.getHeight());
        }

    }

    //pt8
    private static Size chooseOptimalSize(Size[] choices, int width, int height) {
        List<Size> bigEnough = new ArrayList<Size>();
        for (Size option : choices) {
            if (option.getHeight() == option.getWidth() * height / width &&
                    option.getWidth() >= width && option.getHeight() >= height) {
                bigEnough.add(option);
            }
        }
        if (bigEnough.size() > 0) {
            return Collections.min(bigEnough, new CompareSizeByArea());
        } else {
            for (Size option : choices) {
                if (option.getHeight() * option.getWidth() <= height * width) {
                    return option;
                }
            }
            return choices[0];

        }
    }

    //NewClassExample v;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        sm=(SensorManager)getSystemService(SENSOR_SERVICE);
        if(sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size()!=0){
            Sensor s=sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            sm.registerListener(this,s,SensorManager.SENSOR_DELAY_NORMAL);
        }

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        SettingresolutionChanged=false;

        WhiteBalanceBallInspector= BitmapFactory.decodeResource(getResources(),R.drawable.whitebalanceballinspector);

        setContentView(R.layout.activity_camera2_video_image);
        //RGGBChannelMatrix=new Matrix(new double[]{RggbChsnnelR,RggbChannelG_even,RggbChannelG_odd,RggbChannelBlue},1);

        sound=new Sound(this);
        txtfolder=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),"Camera 2 Txt Files");
        PNGRAWfolder=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"Camera2PNG_(fromRaw)");

        Button testbutton1=(Button) findViewById(R.id.testbutton);
        testbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((redPixelData-greePixelData<0)&&(redPixelData-greePixelData>-7)){
                    mVectorR=mVectorR+0.05f;
                }else if(redPixelData-greePixelData>0 && redPixelData-greePixelData<7){
                    mVectorR=mVectorR-0.05f;
                }else if(redPixelData-greePixelData<0){
                    mVectorR=mVectorR-0.1f;
                }else{
                    mVectorR=mVectorR-0.1f;
                }
                startPreview();

            }
        });

        loadingAnimation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotateloading);
        loadingemblem1=(ImageView)findViewById(R.id.loadingemblem);




        //face3=new FaceDetector.Face[3];

         loadingtext=(TextView)findViewById(R.id.loadingtext);


         sharedprefs1 = PreferenceManager.getDefaultSharedPreferences(Camera2VideoImageActivity.this);
         ifOnCreate=sharedprefs1.getBoolean("onCreatePref",true);
        final boolean RawwithJPEg = sharedprefs1.getBoolean("Capture_Raw_With_JPEG", false);
        if(RawwithJPEg){
            AdjustWhiteBalanceonRawCapture=false;
        }
        boolean OpticalStabilization = sharedprefs1.getBoolean("optical_stabilization", true);
        ShowRealTimeInfoboolean=sharedprefs1.getBoolean("show_real_time_info",false);
        CapturePngBoolean=sharedprefs1.getBoolean("Capture_PNG",false);
        ShowCIEXYZValuesBoolean=sharedprefs1.getBoolean("ShowCIEXYZValues",true);
        JPEGCaptureOn=sharedprefs1.getBoolean("Capture_JPEG",true);
        ExposureCompensationSeekBarboolean=sharedprefs1.getBoolean("ExposureCompensationSwitch",false);
        PipelineEditorNumber= Integer.parseInt(sharedprefs1.getString("pipelineEditor","0"));
        ExportasRGBasTextboolean=sharedprefs1.getBoolean("exportRGBasText",false);
        ExportAsRGGBasTextboolean=sharedprefs1.getBoolean("exportRGGBasText",false);
        ExposureCompensationtextview=(TextView)findViewById(R.id.exposure_compensation);
        NoiseReductionModesint=Integer.parseInt(sharedprefs1.getString("noise_reduction_mode","1"));
        AntiBandingModeint = Integer.parseInt(sharedprefs1.getString("control_antibanding_mode","3"));
        PatternTestint=Integer.parseInt(sharedprefs1.getString("sensor_available_test_modes","0"));
        EdgeMode=Integer.parseInt(sharedprefs1.getString("edge_options","1"));
        String TempRecordTimeLimitString=sharedprefs1.getString("RecordTimeStop","0");
        RecordTimeLimit=Integer.parseInt(TempRecordTimeLimitString);
        HotPixelMode=Integer.parseInt(sharedprefs1.getString("hot_pixel_mode","0"));
        ToneMapMode=Integer.parseInt(sharedprefs1.getString("tonemap_mode","1"));
        readRawonTap=sharedprefs1.getBoolean("readRawonTap",false);
        ConvertRAWtoPNG=sharedprefs1.getBoolean("ConvertRAWtoPNG",false);
        Capture_JPEG=sharedprefs1.getBoolean("Capture_JPEG",true);
        JPEGQuality=Byte.parseByte(sharedprefs1.getString("set_jpeg_quality","100"));
        trackfacesbool=sharedprefs1.getBoolean("trackfaces",false);




        ExposureCompensationSeekBar=(SeekBar)findViewById(R.id.ExposureCompensationSeekBar);
        alphaview=(View)findViewById(R.id.alphaview);


        ExposureCompensationtextview.setText(""+ExposureCompensationIntegerProgress);
        if(ExposureCompensationSeekBarboolean) {
            ExposureCompensationSeekBar.setVisibility(View.VISIBLE);
            ExposureCompensationtextview.setVisibility(View.VISIBLE);

        }else{
            ExposureCompensationSeekBar.setVisibility(View.INVISIBLE);
            ExposureCompensationtextview.setVisibility(View.INVISIBLE);
        }
            ExposureCompensationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                    ExposureCompensationIntegerProgress= (float)progress/DenominatorStep;
                    ExposureCompensationtextview.setText("Exposure Compensation : "+(ExposureCompensationIntegerProgress-(((mCameraCharacteristics.get(mCameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE).getUpper())-(mCameraCharacteristics.get(mCameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE).getLower()))/2)));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    startPreview();


                }
            });





        BooleanOpticalStabilizationOn=OpticalStabilization;
        mRawImageCaptureon=RawwithJPEg;







        BallInspectorx = BallInspectory = 600;
        WhiteBalanceBallInspector = BitmapFactory.decodeResource(getResources(), R.mipmap.wbselection);

        SurfaceView Surfaceview = (SurfaceView) findViewById(R.id.surfaceView);
        Surfaceview.setZOrderOnTop(true);
        final SurfaceHolder holder = Surfaceview.getHolder();
        holder.setFormat(PixelFormat.TRANSLUCENT);
        final Surface mWBSurface = holder.getSurface();
        Surfaceview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Bitmap bitmappy=mTextureView.getBitmap();
                pixel = bitmappy.getPixel((int) BallInspectorx, (int) BallInspectory);


                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        BallInspectorx = event.getX();
                        BallInspectory = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        BallInspectorx = event.getX();
                        BallInspectory = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        BallInspectorx = event.getX();
                        BallInspectory = event.getY();
                        break;
                }
                isAdjustingWB=true;
                isAdjustingWB2=true;

                return true;
            }
        });








        //mIsAuto2=false;
        //this is new
        mTextureView = (TextureView) findViewById(R.id.textureView);
        mStillImageButton = (ImageButton) findViewById(R.id.CameraButton);
        mTimeInterval = (TextView) findViewById(R.id.TimeIntervalDisplay);
        mChronometer = (Chronometer) findViewById(R.id.chronometer);
        mFlipCamera = (ImageButton) findViewById(R.id.FlipButton);
        mFlashButtonOnOff = (ImageButton) findViewById(R.id.FlashButton);
        mRecordImageButton = (ImageButton) findViewById(R.id.VideoButton);
        mSettingsButton = (ImageButton) findViewById(R.id.SettingImageButton);
        MovementButtonn = (ImageButton) findViewById(R.id.MovementButton);

        //final int AWBArr[]=new int [mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AWB_AVAILABLE_MODES).length];
        MovementButtonn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (CaptureAveragepixelCountBooleanOn == false) {
                    TotalRedPixelData = 0;
                    TotalBluePixelData = 0;
                    TotalGreenPixelData = 0;
                    MovementButtonnBoolen = true;
                    CaptureAveragepixelCountBooleanOn = true;
                    Toast.makeText(getApplicationContext(), "Average Pixel Activation", Toast.LENGTH_SHORT).show();
                } else {

                    //Toast.makeText(getApplicationContext(), "Fuck you", Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });

        MovementButtonn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wbThreadisEnabled = !wbThreadisEnabled;

                if (MovementButtonnBoolen) {
                    MovementButtonnBoolen = false;
                    MovementButtonn.setImageResource(R.drawable.ic_all_out_black_24dp);
                    Toast.makeText(Camera2VideoImageActivity.this, "Movement Button Turned on", Toast.LENGTH_SHORT).show();

                } else {
                    {
                        if (!CaptureAveragepixelCountBooleanOn) {


                            MovementButtonnBoolen = true;
                            MovementButtonn.setImageResource(R.drawable.ic_highlight_off_black_24dp);
                            Toast.makeText(Camera2VideoImageActivity.this, "Movement Button Turned Off", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }
        });







        final BottomNavigationView mCom = (BottomNavigationView) findViewById(R.id.NavBot);
        mCom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int pposition3 = item.getItemId();
                switch (pposition3) {


                    case R.id.CameraMenu:


                        mStillImageButton.setVisibility(View.VISIBLE);
                        mRecordImageButton.setVisibility(View.INVISIBLE);
                        mSettingsButton.setVisibility(View.INVISIBLE);


                        break;
                    case R.id.VideoMenu:
                        mRecordImageButton.setVisibility(View.VISIBLE);
                        mStillImageButton.setVisibility(View.INVISIBLE);
                        mSettingsButton.setVisibility(View.INVISIBLE);


                        break;
                    case R.id.AdvancedSettingsMenu:


                        mStillImageButton.setVisibility(View.INVISIBLE);
                        mRecordImageButton.setVisibility(View.INVISIBLE);
                        mSettingsButton.setVisibility(View.VISIBLE);
                        mSettingsButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                Intent SettingsIntent=new Intent(getApplicationContext(),SettingsActivity.class);
                                startActivity(SettingsIntent);
                            }
                        });


                        

                        break;
                    case R.id.PageMenu:
                        Intent pageintent = new Intent(getApplicationContext(), TabedMenuActivity.class);
                        startActivity(pageintent);
                        break;
                }
                return false;
            }
        });

        createVideoFolder();
        mMediaRecorder = new MediaRecorder();
        createImageFolder();
        mInfoTextView = (TextView) findViewById(R.id.infotextView2);

        if(ShowRealTimeInfoboolean){
            mInfoTextView.setVisibility(View.VISIBLE);
        }else{
            mInfoTextView.setVisibility(View.INVISIBLE);
        }



        mFocusTextView = (TextView) findViewById(R.id.infoTextView);
        //we have to create a new thread in order to get real time info from ISO SS adn Aperature

        (new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted()) {
                    try {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override


                            public void run() {


                                int SensorReferenceIlluminant = mCameraCharacteristics.get(mCameraCharacteristics.SENSOR_REFERENCE_ILLUMINANT1);


                                String SensorReferenceILluminantString = null;
                                switch (SensorReferenceIlluminant) {
                                    case 10:
                                        SensorReferenceILluminantString = "Cloudy Weather";
                                        break;
                                    case 14:
                                        SensorReferenceILluminantString = "Cool White Fluoresecent";
                                        break;
                                    case 23:
                                        SensorReferenceILluminantString = "D50";
                                        break;
                                    case 20:
                                        SensorReferenceILluminantString = "D55";
                                        break;
                                    case 21:
                                        SensorReferenceILluminantString = "D65";
                                        break;
                                    case 22:
                                        SensorReferenceILluminantString = "D75";
                                        break;
                                    case 1:
                                        SensorReferenceILluminantString = "Daylioght";
                                        break;
                                    case 12:
                                        SensorReferenceILluminantString = "Daylight Fluorescent";
                                        break;
                                    case 13:
                                        SensorReferenceILluminantString = "Day White Fluorescent";
                                        break;
                                    case 9:
                                        SensorReferenceILluminantString = "Fine Weather";
                                        break;
                                    case 4:
                                        SensorReferenceILluminantString = "Flash";
                                        break;
                                    case 2:
                                        SensorReferenceILluminantString = "Fluorescent";
                                        break;
                                    case 24:
                                        SensorReferenceILluminantString = "ISO Studio Tungsten";
                                        break;
                                    case 11:
                                        SensorReferenceILluminantString = "Shade";
                                        break;
                                    case 17:
                                        SensorReferenceILluminantString = "Standardb A";
                                        break;
                                    case 18:
                                        SensorReferenceILluminantString = "Standard B";
                                        break;
                                    case 19:
                                        SensorReferenceILluminantString = "Standard C";
                                        break;
                                    case 3:
                                        SensorReferenceILluminantString = "Tungsten";
                                        break;
                                    case 15:
                                        SensorReferenceILluminantString = "White Fluorescent";
                                        break;

                                }
                                if (cameraReady){

                                    if(readRawonTap) {
                                        if (isAdjustingWB && isAdjustingWB2) {
                                            isAdjustingWB = false;
                                            if(!MovementButtonnBoolen){
                                                /*loadingalphabool=true;
                                                if(loadingalphabool==true){

                                                    loadingalphaboolinit=false;
                                                    loadingemblem1.setVisibility(View.VISIBLE);
                                                    alphaview.setAlpha(0.5f);
                                                    loadingemblem1.setAnimation(loadingAnimation);

                                                }else if (loadingalphabool==false){
                                                    loadingemblem1.setVisibility(View.INVISIBLE);
                                                    alphaview.setAlpha(0.0f);

                                                }*/

                                                        loadingtext.setVisibility(View.VISIBLE);
                                                        loadingemblem1.setVisibility(View.VISIBLE);

                                                        loadingemblem1.setAnimation(loadingAnimation);
                                                        loadingAnimation.setRepeatCount(Animation.INFINITE);
                                                        alphaview.setAlpha(0.5f);






                                                adjustWhiteBalanceOnTouch();
                                            }

                                        }
                                    }
                                    redPixelData = Color.red(pixel);
                                    bluePixelData = Color.blue(pixel);
                                    greePixelData = Color.green(pixel);
                                    if (wbThreadisEnabled) {
                                        //Toast.makeText(Camera2VideoImageActivity.this, "Part 3", Toast.LENGTH_SHORT).show();
                                        //Canvas circleCanvas=holder.lockCanvas();
                                        //circleCanvas.drawColor(Color.TRANSPARENT,PorterDuff.Mode.CLEAR);
                                        if(WhiteBalanceBallInspector!=null){
                                            //circleCanvas.drawBitmap(WhiteBalanceBallInspector,BallInspectorx,BallInspectory,null);
                                        }
                                        //holder.unlockCanvasAndPost(circleCanvas);

                                    }else{ /*
                                        Canvas circleCanvas=holder.lockCanvas();
                                        circleCanvas.drawColor(Color.TRANSPARENT,PorterDuff.Mode.CLEAR);
                                        if(WhiteBalanceBallInspector !=null){
                                            circleCanvas.drawBitmap(WhiteBalanceBallInspector,0,0,null);
                                        }
                                        holder.unlockCanvasAndPost(circleCanvas);*/
                                    }






                                /*if (ChangeWhiteBalanceSpotRawOn) {
                                    ChangeWhiteBalanceSpotRawOn = false;

                                }*/


                                if (mWBSurface.isValid()) {

                                    Bitmap bitmappy2 = mTextureView.getBitmap();
                                    ByteBuffer bytebuffer1 = ByteBuffer.allocate(1);




                                    int totalheight = WhiteBalanceBallInspector.getHeight();
                                    int totalwidth = WhiteBalanceBallInspector.getWidth();
                                    int totalbitmapspace = totalheight * totalwidth;
                                    int topleftheightstatic = (int) BallInspectory - (int) (totalheight / 2);
                                    int topleftwidthstatic = (int) BallInspectorx - (int) (totalwidth / 2);
                                    int topleftheight;
                                    int topeleftwidth;



                                    if (CaptureAveragepixelCountBooleanOn) {

                                        for (topleftheight = (int) BallInspectory - (totalheight / 2); topleftheight < (totalheight + topleftheightstatic); topleftheight++) {
                                            for (topeleftwidth = (int) BallInspectorx - (totalwidth / 2); topeleftwidth < (totalwidth + topleftwidthstatic); topeleftwidth++) {
                                                int pixel2;
                                                pixel2 = bitmappy2.getPixel((int) topeleftwidth, (int) topleftheight);
                                                if (Color.red(pixel2) < 255) {
                                                    TotalRedPixelData = TotalRedPixelData + Color.red(pixel2);
                                                    TotalGreenPixelData = TotalGreenPixelData + Color.green(pixel2);
                                                    TotalBluePixelData = TotalBluePixelData + Color.blue(pixel2);
                                                    AverageredPixelData = (TotalRedPixelData / totalbitmapspace);
                                                    AveragegreenPixelData = TotalGreenPixelData / totalbitmapspace;
                                                    AveragebluePixelData = TotalBluePixelData / totalbitmapspace;


                                                }
                                            }

                                        }
                                        CaptureAveragepixelCountBooleanOn = false;
                                    }



                                        Canvas c = holder.lockCanvas();
                                    c.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                                    if(trackfacesbool && starttrackingbool){
                                       // mNumberofFaces2=facedetector1.findFaces(bitmappy2,face3);
                                        if(mNumberofFaces2>=1){
                                            //Here I was trying to implement a Face Detector, but its too slow

                                           // float eyedistance;
                                         //   eyedistance=face3[0].eyesDistance();
                                        }

                                    }
                                    if(trackfacesbool && !previewinit &&  starttrackingbool){
                                        //this rectangle represents the raw data taken in from the SENSOR_ACTIVE_ARRAY
                                        Paint mypaint=new Paint();
                                        mypaint.setColor(Color.rgb(100,100,0));
                                        mypaint.setStrokeWidth(0);
                                        c.drawRect(faces[0].getBounds().left,faces[0].getBounds().top,faces[0].getBounds().right,faces[0].getBounds().bottom, mypaint);

                                    }
                                    if(trackfacesbool && !previewinit &&  starttrackingbool){
                                        Paint mypaint2=new Paint();
                                        mypaint2.setColor(Color.rgb(100,0,100));
                                        mypaint2.setStrokeWidth(0);
                                        c.drawRect(leftface,topface,rightface,bottomface, mypaint2);

                                    }



                                        if (!MovementButtonnBoolen || CaptureAveragepixelCountBooleanOn) {
                                            if (WhiteBalanceBallInspector != null) {
                                                c.drawBitmap(WhiteBalanceBallInspector, BallInspectorx - (WhiteBalanceBallInspector.getWidth() / 2), BallInspectory - (WhiteBalanceBallInspector.getHeight() / 2), null);
                                            }
                                        }

                                        holder.unlockCanvasAndPost(c);



                                }
                                String convertSS;
                                String PixelValues;
                                StringBuffer sbuffer = new StringBuffer();
                                CIEXYZvaluesString = "";

                                if (ShowCIEXYZValuesBoolean) {
                                    double[] RGB = new double[]{redPixelData, greePixelData, bluePixelData};
                                    double[][] RGB2XYZ = {{0.4124564, 0.3575761, 0.1804375}, {0.2126729, 0.7151522, 0.0721750}, {0.0193339, 0.1191920, 0.9503041}};
                                    int rowRGB = RGB.length;
                                    int columnsInRGB = 1;

                                    int rowRGB2XYZ = RGB2XYZ.length;
                                    int columnRGB2XYZ = RGB2XYZ[0].length;
                                    cArray = new double[rowRGB][columnRGB2XYZ];


                                    for (int i = 0; i < rowRGB; i++) {
                                        for (int j = 0; j < columnRGB2XYZ; j++) {
                                            for (int k = 0; k < columnsInRGB; k++) {
                                                cArray[i][j] = cArray[i][j] + RGB[i] * RGB2XYZ[k][j];

                                            }
                                        }
                                    }
                                    for (int i = 0; i < cArray.length; i++) {
                                        for (int j = 0; j < cArray[0].length; j++) {
                                            //CIEXYZvaluesString=(cArray[i][j]+"");
                                            sbuffer.append(" " + cArray[i][j] + " ");
                                        }

                                    }

                                    CIEXYZvaluesString = "   CIE XYZ VALUES: " + sbuffer.toString();


                                    //launch special Method

                                }


                                if (MovementButtonnBoolen == false || CaptureAveragepixelCountBooleanOn || wbThreadisEnabled) {
                                    PixelValues = "Red Value: " + redPixelData
                                            + " Green Pixel Data : " + greePixelData + " Blue Pixel Data :" + bluePixelData
                                            + " Average Red Value :" + AverageredPixelData + " Average Green Value : " + AveragegreenPixelData + " Average Blue Value : "
                                            + AveragebluePixelData + CIEXYZvaluesString
                                    ;
                                } else {
                                    PixelValues = "";
                                }
                                mTotalRotation = sensorDeviceRotation(mCameraCharacteristics, mDeviceOrientation);

                                if (1000000000 / mCurrentSSvalue <= 1) {
                                    convertSS = String.valueOf(mCurrentSSvalue / 1000000000);
                                } else {
                                    convertSS = "1/" + String.valueOf(1000000000 / mCurrentSSvalue);
                                }
                                if (1 / mCurrentFocusDistance < 1 / mMaxFocusDistance - 0.1) {
                                    mInfoTextView.setText("ISO: " + mCurrentISOValue + "\t\t\t\t" + "Shutter Speed:" + convertSS + "\t\t\t\t" + "Focus Distance: " + String.format("%.2f", 100 / mCurrentFocusDistance) + "cm" + "\t\t\t\t" + "Faces Detected:" +
                                            mNumberofFaces + "\t\t\t\t" + rggbChannelVector + "\t\t\t\t" + ColorCorrectionTransform + "\t\t\t\t" + "X-coord: " + BallInspectorx + "\t\t\t\t" + "Y-coord: " + BallInspectory + "\t\t\t\t\t"+"Sensor Reference Illuminant 1: " + SensorReferenceILluminantString + "\t\t\t\t" + PixelValues


                                    );

                                } else if (1 / mCurrentFocusDistance > 1 / mMaxFocusDistance - 0.1) {
                                    mInfoTextView.setText("ISO: " + mCurrentISOValue + "\t\t\t\t" + "Shutter Speed: " + convertSS + "\t\t\t\t" + "Focus Distance: " + "INFINITE"
                                            + "\t\t\t\t" + "Faces Detected:" + mNumberofFaces + "\t\t\t\t" + rggbChannelVector + "\t\t\t\t" + ColorCorrectionTransform + "\t\t\t\t" + "X-coord" + BallInspectorx + "\t\t\t\t" + "Y-coord" + BallInspectory + "\t\t\t\t" + "Sensor Reference Illuminant 1: " + SensorReferenceILluminantString + "\t\t\t\t" + PixelValues

                                    );
                                }
                                if (rggbChannelVector != null) {
                                    if (!CustomeWhiteBalanceBoolean) {
                                        RggbChsnnelR = (double) rggbChannelVector.getRed();
                                        RggbChannelBlue = (double) rggbChannelVector.getBlue();
                                        RggbChannelG_odd = (double) rggbChannelVector.getGreenOdd();
                                        RggbChannelG_even = (double) rggbChannelVector.getGreenEven();


                                    }

                                }
                            }






                            }
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        })).start();

        Intent intent = getIntent();
        String action = intent.getAction();
        if (MediaStore.ACTION_IMAGE_CAPTURE.equals(action)) {
            mRequestingAppUri = intent.getParcelableExtra(MediaStore.EXTRA_OUTPUT);
        }

        //

        mFlashButtonOnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Toast.makeText(getApplicationContext(), "Flash", Toast.LENGTH_SHORT).show();
                PopupMenu popMenu2 = new PopupMenu(Camera2VideoImageActivity.this, mFlashButtonOnOff);

                popMenu2.inflate(R.menu.flash_popup_menu);
                popMenu2.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int position2 = item.getItemId();
                        switch (position2) {
                            case R.id.FlashOff:
                                mFlashButtonOnOff.setImageResource(R.drawable.ic_flash_off_black_24dp);
                                mFlashMode = 0;
                                startPreview();


                                break;
                            case R.id.FlashAuto:
                                mFlashButtonOnOff.setImageResource(R.drawable.ic_flash_auto_black_24dp);
                                mFlashMode = 1;


                                break;
                            case R.id.FlashOn:
                                mFlashButtonOnOff.setImageResource(R.drawable.ic_flash_on_black_24dp);
                                mFlashMode = 2;
                                break;
                            case R.id.TorchOn:
                                mFlashButtonOnOff.setImageResource(R.drawable.ic_highlight_black_24dp);
                                mFlashMode = 3;
                                startPreview();
                                break;


                        }
                        return false;
                    }
                });

                popMenu2.show();


            }
        });


        mFlipCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FlipNumberBoolean) {

                    FlipNumberBoolean = false;
                    FlipNumber = 0;
                    mFlipCamera.setImageResource(R.drawable.flipfront);
                    closeCamera();
                    stopBackgroundThread();

                    startBackgroundThread();

                    if (mTextureView.isAvailable()) {
                        setupCamera(mTextureView.getWidth(), mTextureView.getHeight());
                        connectCamera();

                    } else {
                        mTextureView.setSurfaceTextureListener(mSurfaceTextureListener);
                    }
                } else {

                    FlipNumberBoolean = true;
                    FlipNumber = 1;
                    mFlipCamera.setImageResource(R.drawable.flipback);
                    closeCamera();
                    stopBackgroundThread();
                    startBackgroundThread();
                    if (mTextureView.isAvailable()) {
                        setupCamera(mTextureView.getWidth(), mTextureView.getHeight());
                        connectCamera();
                    } else {
                        mTextureView.setSurfaceTextureListener(mSurfaceTextureListener);
                    }


                }
            }
        });
        mModebutton = (Button) findViewById(R.id.button);
        mAutobutton = (Button) findViewById(R.id.Auto);
        mAutobutton.setText("AUTO ON");
        mAutobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AutoNumber == 0) {
                    AutoNumber = 1;
                    Toast.makeText(getApplicationContext(), "AUTO OFF", Toast.LENGTH_SHORT).show();
                    mAutobutton.setText("Reset");

                    startPreview();

                } else if (AutoNumber == 1) {
                    AutoNumber = 0;
                    Toast.makeText(getApplicationContext(), "AUTO ON", Toast.LENGTH_SHORT).show();
                    mAutobutton.setText("AUTO ON");
                    ColorSpaceInputBoolean = false;
                    //connectCamera();
                    startPreview();


                } else if (AutoNumber == 2) {
                    AutoNumber = 0;
                    Toast.makeText(getApplicationContext(), "SceneAutoOff", Toast.LENGTH_SHORT).show();
                    mAutobutton.setText("AUTO ON");
                    startPreview();

                }
            }

        });
        mAutobutton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AutoNumber = 2;

                Toast.makeText(getApplicationContext(), "AutoNumber now 2", Toast.LENGTH_SHORT).show();
                mAutobutton.setText("AUTO SCENE");
                startPreview();

                return true;
            }
        });
        mModebutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                menuonline = true;

                //Toast.makeText(Camera2VideoImageActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                final PopupMenu popupMenu = new PopupMenu(Camera2VideoImageActivity.this, mModebutton);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                SubMenu submenu2 = popupMenu.getMenu().addSubMenu(0, 100, 0, "Available Effects");
                SubMenu submenu3= popupMenu.getMenu().addSubMenu(0, 200,0,"Available Scenes");


                if (WBrunOnce) {
                    for (int i = 0; i < mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AWB_AVAILABLE_MODES).length; i++) {
                        if (mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AWB_AVAILABLE_MODES)[i] == 2) {
                            ControlAWBmodeincandescentavailableboolean = true;
                        }
                        if (mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AWB_AVAILABLE_MODES)[i] == 3) {
                            ControlAWBmodefluorescentavailableboolean = true;
                        }
                        if (mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AWB_AVAILABLE_MODES)[i] == 4) {
                            ControlAWBmodewarmfluorescentavailableboolean = true;
                        }
                        if (mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AWB_AVAILABLE_MODES)[i] == 5) {
                            ControlAWBmodedaylightavailableboolean = true;
                        }
                        if (mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AWB_AVAILABLE_MODES)[i] == 6) {
                            ControlAWBmodecloudydaylightavailableboolean = true;
                        }
                        if (mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AWB_AVAILABLE_MODES)[i] == 7) {
                            ControlAWBmodetwilightavailableboolean = true;

                        }
                        if (mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AWB_AVAILABLE_MODES)[i] == 8) {
                            ControlAWBmodeshadeavailableboolean = true;

                        }
                    }
                    WBrunOnce = false;
                }


                final MenuItem AutoWhiteBalanceItem = popupMenu.getMenu().findItem(R.id.LockWhiteBalance);
                AutoWhiteBalanceItem.setChecked(AutoWhiteBalancelockBoolean);

                final MenuItem WhiteBalanceCloudyDaylightItem = popupMenu.getMenu().findItem(R.id.WhiteBalanceCloudyDaylight);
                WhiteBalanceCloudyDaylightItem.setChecked(WhiteBalanceCloudyDaylightBoolean);
                WhiteBalanceCloudyDaylightItem.setEnabled(ControlAWBmodecloudydaylightavailableboolean);
                final MenuItem WhiteBalanceDaylightItem = popupMenu.getMenu().findItem(R.id.WhiteBalanceDaylight);
                WhiteBalanceDaylightItem.setChecked(WhiteBalanceDaylightBoolean);
                WhiteBalanceDaylightItem.setEnabled(ControlAWBmodedaylightavailableboolean);
                final MenuItem WhiteBalanceFluorescentItem = popupMenu.getMenu().findItem(R.id.WhiteBalanceFluorescent);
                WhiteBalanceFluorescentItem.setChecked(WhiteBalanceFluorescentBoolean);
                WhiteBalanceFluorescentItem.setEnabled(ControlAWBmodefluorescentavailableboolean);
                final MenuItem WhiteBalanceShadeItem = popupMenu.getMenu().findItem(R.id.WhiteBalanceShade);
                WhiteBalanceShadeItem.setChecked(WhiteBalanceShadeBoolean);
                WhiteBalanceShadeItem.setEnabled(ControlAWBmodeshadeavailableboolean);
                final MenuItem WhiteBalanceTwilightitem = popupMenu.getMenu().findItem(R.id.WhiteBalanceTwilight);
                WhiteBalanceTwilightitem.setChecked(WhiteBalanceTwilightBoolean);
                WhiteBalanceTwilightitem.setEnabled(ControlAWBmodetwilightavailableboolean);
                final MenuItem WhiteBalanceWarmFluorescentItem = popupMenu.getMenu().findItem(R.id.WhiteBalanceWarmFluorescent);
                WhiteBalanceWarmFluorescentItem.setChecked(WhiteBalanceWarmFluorescentBoolean);
                WhiteBalanceWarmFluorescentItem.setEnabled(ControlAWBmodewarmfluorescentavailableboolean);
                final MenuItem WhiteBalanceIncandenscentItem = popupMenu.getMenu().findItem(R.id.WhiteBalanceIncandenscent);
                WhiteBalanceIncandenscentItem.setChecked(WhiteBalanceIncandenscentBoolean);
                WhiteBalanceIncandenscentItem.setEnabled(ControlAWBmodeincandescentavailableboolean);
                final MenuItem WhiteBalanceAutoItem = popupMenu.getMenu().findItem(R.id.WhiteBalanceAuto);
                WhiteBalanceAutoItem.setChecked(WhiteBalanceAutoBoolean);
                final MenuItem ColorSpaceCheckedItem=popupMenu.getMenu().findItem(R.id.ColorSpaceInput);
                ColorSpaceCheckedItem.setChecked(ColorSpaceInputBoolean);
                final MenuItem WhiteBalanceCheckedItem=popupMenu.getMenu().findItem(R.id.CustomWhiteBalance);
                WhiteBalanceCheckedItem.setChecked(CustomeWhiteBalanceBoolean);





                final int[] SupportedSceneModes = new int[mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AVAILABLE_SCENE_MODES).length];


                for (int i = 0; i < SupportedSceneModes.length; i++) {
                    SupportedSceneModes[i] = mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AVAILABLE_SCENE_MODES)[i];

                }
                final int[] AvailableEffectsArray1 = new int[mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AVAILABLE_EFFECTS).length];
                for (int i = 0; i < AvailableEffectsArray1.length; i++) {
                    AvailableEffectsArray1[i] = (mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AVAILABLE_EFFECTS)[i]);
                }
                final String[] AvailableEffectsArray2 = new String[AvailableEffectsArray1.length];
                for (int i = 0; i < AvailableEffectsArray1.length; i++) {
                    if (AvailableEffectsArray1[i] == 0) {
                        AvailableEffectsArray2[i] = "OFF";
                    }
                    if (AvailableEffectsArray1[i] == 1) {
                        AvailableEffectsArray2[i] = "Mono";
                    }
                    if (AvailableEffectsArray1[i] == 2) {
                        AvailableEffectsArray2[i] = "Negative";
                    }
                    if (AvailableEffectsArray1[i] == 3) {
                        AvailableEffectsArray2[i] = "Solarize";
                    }
                    if (AvailableEffectsArray1[i] == 4) {
                        AvailableEffectsArray2[i] = "Sepia";
                    }
                    if (AvailableEffectsArray1[i] == 5) {
                        AvailableEffectsArray2[i] = "Posterize";
                    }
                    if (AvailableEffectsArray1[i] == 6) {
                        AvailableEffectsArray2[i] = "Whiteboard";
                    }
                    if (AvailableEffectsArray1[i] == 7) {
                        AvailableEffectsArray2[i] = "Blackboard";
                    }
                    if (AvailableEffectsArray1[i] == 8) {
                        AvailableEffectsArray2[i] = "Aqua";
                    }
                    submenu2.add(0, i + 100, 0, "" + AvailableEffectsArray2[i]);

                }
                final int[] Scenearray=new int[mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AVAILABLE_SCENE_MODES).length];

                for(int i=0;i<mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AVAILABLE_SCENE_MODES).length;i++){
                    Scenearray[i]=(mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AVAILABLE_SCENE_MODES)[i]);

                }

                final String[] Scenearray2=new String[Scenearray.length];
                for(int i=0;i<Scenearray.length;i++){
                    switch(Scenearray[i]){
                        case 0:
                            Scenearray2[i]="Disable";

                            break;
                        case 1:
                            Scenearray2[i]="Face Priority";

                            break;
                        case 2:Scenearray2[i]="Action Scene";

                            break;
                        case 3:Scenearray2[i]="Portrait ";

                            break;
                        case 4:Scenearray2[i]="Landscape";

                            break;
                        case 5:Scenearray2[i]="Night ";

                            break;
                        case 6:Scenearray2[i]="Night Portrait";

                            break;
                        case 7:Scenearray2[i]="Theatre";

                            break;
                        case 8:Scenearray2[i]="Beach";

                            break;
                        case 9:Scenearray2[i]="Snow";

                            break;
                        case 10:Scenearray2[i]="Sunset";

                            break;
                        case 11:Scenearray2[i]="Steadyphoto";

                            break;
                        case 12:Scenearray2[i]="Fireworks";

                            break;
                        case 13:Scenearray2[i]="Sports";

                            break;
                        case 14:Scenearray2[i]="Party";

                            break;
                        case 15:Scenearray2[i]="Candlelight";

                            break;
                        case 16:Scenearray2[i]="Barcode";

                            break;
                        case 18:Scenearray2[i]="HDR";

                            break;

                    }
                    submenu3.add(0,i+200,0,""+Scenearray2[i]);

                }


                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        //add settings
                        int position = item.getItemId();

                        for (int i = 0; i < AvailableEffectsArray2.length; i++) {
                            if (position == 100 + i) {
                                mCameraEffect = AvailableEffectsArray1[i];
                                startPreview();
                            }

                        }
                        for(int i=0;i<Scenearray2.length;i++){
                            if(position==200+i){
                                mSceneMode=Scenearray[i];
                                startPreview();
                            }
                        }


                        final Range<Long> ShutterSpeed = mCameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_EXPOSURE_TIME_RANGE);
                        final long ShutterSpeed1 = (ShutterSpeed.getLower());

                        final long ShutterSpeed2 = (ShutterSpeed.getUpper());
                        //Toast.makeText(getApplicationContext(), "ShutterSpeedMax: "+ ShutterSpeed2, Toast.LENGTH_LONG).show();
                        double ShutterSpeed1Double = (double) ShutterSpeed1 / 1000000000;
                        double ShutterSpeed2Double = (double) ShutterSpeed2 / 1000000000;
                        //trying to convert to fractions
                        double x = 1 / ShutterSpeed1Double;
                        if (ShutterSpeed2Double <= 1) {
                            double y = 1 / ShutterSpeed2Double;
                            ShutterSpeed2String = ("1" + "/" + (int) y);
                        } else {
                            double y = ShutterSpeed2Double;
                            ShutterSpeed2String = ("" + (int) y);

                        }
                        ShutterSpeed1String = ("1" + "/" + (int) x);
                        //since ShutterSpeed1 is usually a fraction anyways

                        mISOtext = (EditText) findViewById(R.id.ISOtext);
                        if (ISOvalue == 0) {
                            mISOtext.setText("ISO:AUTO");
                        }

                        final Range<Integer> ISOrange = mCameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_SENSITIVITY_RANGE);
                        final int LowestISO = ISOrange.getLower();
                        final int HighestISO = ISOrange.getUpper();


                        mChangeFocusSeekBar = (SeekBar) findViewById(R.id.FocusChangeSeekBar);
                        mChangeFocusSeekBar.setMax((int) ((int) (1 / mMaxFocusDistance - 1 / mMinFocusDistance) / 0.05));

                        mCloseALLbutton = (ImageButton) findViewById(R.id.CloseALLbutton);
                        mCloseALLbutton.setVisibility(View.VISIBLE);
                        mSeekbar = (SeekBar) findViewById(R.id.seekBar);
                        mISOseekbar = (SeekBar) findViewById(R.id.ISOseekbar);
                        mTextSeekBar = (EditText) findViewById(R.id.editText);
                        mMinimumShutterSpeed = (EditText) findViewById(R.id.MinimumShutterSpeed);
                        mMaximumShutterSpeed = (EditText) findViewById(R.id.MaximumShutterSpeed);


                        mCloseALLbutton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (mISOtext.getVisibility() == View.VISIBLE) {
                                    mISOtext.setVisibility(View.INVISIBLE);
                                }
                                if (mFocusTextView.getVisibility() == View.VISIBLE) {
                                    mFocusTextView.setVisibility(View.INVISIBLE);
                                }

                                if (mSeekbar.getVisibility() == View.VISIBLE) {
                                    mSeekbar.setVisibility(View.INVISIBLE);
                                }
                                if (mISOseekbar.getVisibility() == View.VISIBLE) {
                                    mISOseekbar.setVisibility(View.INVISIBLE);
                                }
                                if (mTextSeekBar.getVisibility() == View.VISIBLE) {
                                    mTextSeekBar.setVisibility(View.INVISIBLE);
                                }
                                if (mMinimumShutterSpeed.getVisibility() == View.VISIBLE) {
                                    mMinimumShutterSpeed.setVisibility(View.INVISIBLE);
                                }
                                if (mMaximumShutterSpeed.getVisibility() == View.VISIBLE) {
                                    mMaximumShutterSpeed.setVisibility(View.INVISIBLE);
                                }
                                if (mChangeFocusSeekBar.getVisibility() == View.VISIBLE) {
                                    mChangeFocusSeekBar.setVisibility(View.INVISIBLE);
                                }
                                if (mTimeInterval.getVisibility() == View.VISIBLE) {
                                    mTimeInterval.setVisibility(View.INVISIBLE);
                                }


                            }
                        });

                        //mRawCheckBox = (CheckBox) findViewById(R.id.RawInput);

                        switch (position) {
                            case R.id.LockAutoFocus:

                                if (!lockFocusEnableIsChecked) {
                                    lockFocusEnableIsChecked = true;
                                    Toast.makeText(getApplicationContext(), "AutoFocus lock Enabled", Toast.LENGTH_SHORT).show();
                                    //UnlockFocusSpecialBooleanCaptureon=true;


                                    startPreview();


                                } else if (lockFocusEnableIsChecked) {
                                    lockFocusEnableIsChecked = false;
                                    Toast.makeText(getApplicationContext(), "AutoFocus Unlock Enabled", Toast.LENGTH_SHORT).show();

                                    //mFocusTextView.setVisibility(View.INVISIBLE);


                                    UnlockFocusSpecialBooleanCaptureon = false;
                                    startPreview();

                                }
                                break;


                            case R.id.manualFocus:
                                if (!manualFocusEnableIsChecked) {
                                    manualFocusEnableIsChecked = true;
                                    mFocusTextView.setVisibility(View.VISIBLE);
                                    Toast.makeText(getApplicationContext(), "Manual Focus Activated", Toast.LENGTH_SHORT).show();

                                    startPreview();
                                } else if (manualFocusEnableIsChecked) {
                                    manualFocusEnableIsChecked = false;
                                    mFocusTextView.setVisibility(View.INVISIBLE);
                                    Toast.makeText(getApplicationContext(), "Auto Focus Enabled", Toast.LENGTH_SHORT).show();
                                    startPreview();
                                }
                                if (mChangeFocusSeekBar.getVisibility() == View.VISIBLE) {
                                    mChangeFocusSeekBar.setVisibility(View.INVISIBLE);
                                } else if (mChangeFocusSeekBar.getVisibility() == View.INVISIBLE) {

                                    mChangeFocusSeekBar.setVisibility(View.VISIBLE);
                                    mChangeFocusSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                                        @Override
                                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                            mFocusDistance = (progress * 0.05);
                                            mFocusTextView.setText(String.format("Focal Distance: " + "%.2f", mFocusDistance) + "m");
                                        }

                                        @Override
                                        public void onStartTrackingTouch(SeekBar seekBar) {

                                        }

                                        @Override
                                        public void onStopTrackingTouch(SeekBar seekBar) {
                                            startPreview();

                                        }
                                    });
                                }
                                //startPreview();
                                break;
                            case R.id.manualinputFocus:

                                //Toast.makeText(getApplicationContext(), "Not implemented yet", Toast.LENGTH_SHORT).show();
                                LayoutInflater inflate5 = LayoutInflater.from(Camera2VideoImageActivity.this);
                                View ThemanualinputView = inflate5.inflate(R.layout.manual_focus_input, null);
                                AlertDialog.Builder manualinputalert = new AlertDialog.Builder(Camera2VideoImageActivity.this);
                                manualinputalert.setTitle("Manual Focus Input");
                                manualinputalert.setView(ThemanualinputView);
                                manualinputalert.setCancelable(true);
                                mManualFocusInput = (EditText) ThemanualinputView.findViewById(R.id.FocusEditText);
                                manualinputalert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();

                                    }
                                });
                                manualinputalert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (!manualFocusEnableIsChecked) {
                                            manualFocusEnableIsChecked = true;
                                            mFocusTextView.setVisibility(View.VISIBLE);
                                            Toast.makeText(getApplicationContext(), "Manual Focus Activated", Toast.LENGTH_SHORT).show();


                                        } else if (manualFocusEnableIsChecked) {
                                            manualFocusEnableIsChecked = false;
                                            mFocusTextView.setVisibility(View.INVISIBLE);
                                            Toast.makeText(getApplicationContext(), "Auto Focus Enabled", Toast.LENGTH_SHORT).show();

                                        }

                                        double TempManualFocusInput = Double.parseDouble(mManualFocusInput.getText().toString());
                                        mFocusTextView.setText(TempManualFocusInput + "");
                                        mFocusDistance = TempManualFocusInput;

                                    }
                                });
                                manualinputalert.show();
                                startPreview();


                                break;



                            case R.id.ColorSpaceInput:

                                if (ColorSpaceInputBoolean) {
                                    ColorSpaceInputBoolean = false;
                                    Toast.makeText(getApplicationContext(), "Colour SpaceTurned OFF", Toast.LENGTH_SHORT).show();
                                    startPreview();
                                } else {
                                    //ColorSpaceInputBoolean=true;
                                    Toast.makeText(getApplicationContext(), "Colour Space Turned ON", Toast.LENGTH_SHORT).show();


                                    final LayoutInflater ColorSpaceinflater = LayoutInflater.from(Camera2VideoImageActivity.this);
                                    final View ColourSpaceView = ColorSpaceinflater.inflate(R.layout.colorspaceinput, null);
                                    final AlertDialog.Builder ColourSpaceThing = new AlertDialog.Builder(Camera2VideoImageActivity.this);
                                    ColourSpaceThing.setTitle("Colour Space input : ");
                                    ColourSpaceThing.setView(ColourSpaceView);
                                    ColourSpaceThing.setCancelable(true);
                                    //EditTextBoxes
                                    mColorSpaceText1 = (EditText) ColourSpaceView.findViewById(R.id.WhiteBalanceInputEditText1);
                                    mColorSpaceText2 = (EditText) ColourSpaceView.findViewById(R.id.WhiteBalanceInputEditText2);
                                    mColorSpaceText3 = (EditText) ColourSpaceView.findViewById(R.id.WhiteBalanceInputEditText3);
                                    mColorSpaceText4 = (EditText) ColourSpaceView.findViewById(R.id.WhiteBalanceInputEditText4);
                                    mColorSpaceText5 = (EditText) ColourSpaceView.findViewById(R.id.WhiteBalanceInputEditText5);
                                    mColorSpaceText6 = (EditText) ColourSpaceView.findViewById(R.id.WhiteBalanceInputEditText6);
                                    mColorSpaceText7 = (EditText) ColourSpaceView.findViewById(R.id.WhiteBalanceInputEditText7);
                                    mColorSpaceText8 = (EditText) ColourSpaceView.findViewById(R.id.WhiteBalanceInputEditText8);
                                    mColorSpaceText9 = (EditText) ColourSpaceView.findViewById(R.id.WhiteBalanceInputEditText9);

                                    ColourSpaceThing.setNegativeButton("Close", new DialogInterface.OnClickListener() {


                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            ColorSpaceInputBoolean = false;
                                            Toast.makeText(getApplicationContext(), "Colour SpaceTurned OFF", Toast.LENGTH_SHORT).show();

                                        }
                                    });

                                    ColourSpaceThing.setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            if (mColorSpaceText1.getText().toString().isEmpty() ||
                                                    mColorSpaceText1.getText().toString().isEmpty() ||
                                                    mColorSpaceText2.getText().toString().isEmpty() ||
                                                    mColorSpaceText3.getText().toString().isEmpty() ||
                                                    mColorSpaceText4.getText().toString().isEmpty() ||
                                                    mColorSpaceText5.getText().toString().isEmpty() ||
                                                    mColorSpaceText6.getText().toString().isEmpty() ||
                                                    mColorSpaceText7.getText().toString().isEmpty() ||
                                                    mColorSpaceText8.getText().toString().isEmpty() ||
                                                    mColorSpaceText9.getText().toString().isEmpty()) {
                                                Toast.makeText(Camera2VideoImageActivity.this, "All Fields must be filled", Toast.LENGTH_SHORT).show();
                                            } else {



                                            ColorSpaceInputBoolean = true;
                                            int TempWhiteBalanceInputEditText1 = Integer.parseInt(mColorSpaceText1.getText().toString());
                                            int TempWhiteBalanceInputEditText2 = Integer.parseInt(mColorSpaceText2.getText().toString());
                                            int TempWhiteBalanceInputEditText3 = Integer.parseInt(mColorSpaceText3.getText().toString());
                                            int TempWhiteBalanceInputEditText4 = Integer.parseInt(mColorSpaceText4.getText().toString());
                                            int TempWhiteBalanceInputEditText5 = Integer.parseInt(mColorSpaceText5.getText().toString());
                                            int TempWhiteBalanceInputEditText6 = Integer.parseInt(mColorSpaceText6.getText().toString());
                                            int TempWhiteBalanceInputEditText7 = Integer.parseInt(mColorSpaceText7.getText().toString());
                                            int TempWhiteBalanceInputEditText8 = Integer.parseInt(mColorSpaceText8.getText().toString());
                                            int TempWhiteBalanceInputEditText9 = Integer.parseInt(mColorSpaceText9.getText().toString());
                                            ColorSpaceRed1 = TempWhiteBalanceInputEditText1;
                                            ColorSpaceRed2 = TempWhiteBalanceInputEditText2;
                                            ColorSpaceRed3 = TempWhiteBalanceInputEditText3;
                                            ColorSpaceGreen1 = TempWhiteBalanceInputEditText4;
                                            ColorSpaceGreen2 = TempWhiteBalanceInputEditText5;
                                            ColorSpaceGreen3 = TempWhiteBalanceInputEditText6;
                                            ColorSpaceBlue1 = TempWhiteBalanceInputEditText7;
                                            ColorSpaceBlue2 = TempWhiteBalanceInputEditText8;
                                            ColorSpaceBlue3 = TempWhiteBalanceInputEditText9;
                                            startPreview();

                                        }
                                        }
                                    });
                                    ColourSpaceThing.show();
                                }

                                break;
                            case R.id.BasicManualMode:
                                if (AutoNumber == 0) {
                                    AutoNumber = 1;
                                    Toast.makeText(getApplicationContext(), "AUTO OFF", Toast.LENGTH_SHORT).show();
                                    mAutobutton.setText("AUTO OFF");
                                }
                                startPreview();
                                break;
                            case R.id.AdvancedManualMode:

                                break;

                            case R.id.LockWhiteBalance:
                                if (AutoWhiteBalancelockBoolean) {
                                    AutoWhiteBalancelockBoolean = false;
                                    item.setChecked(false);
                                    //wip
                                    //AutoWhiteBalanceUnlock();
                                } else {
                                    //wip
                                    //AutoWhiteBalanceLock();
                                    item.setChecked(true);
                                    AutoWhiteBalancelockBoolean = true;
                                }
                                //lock if unlocked
                                //unlock if lock
                                startPreview();
                                break;
                            case R.id.CustomWhiteBalance:
                                if (CustomeWhiteBalanceBoolean) {
                                    CustomeWhiteBalanceBoolean = false;
                                    mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AWB_MODE, CameraMetadata.CONTROL_AWB_MODE_AUTO);
                                    Toast.makeText(getApplicationContext(), "Color Correction Auto", Toast.LENGTH_SHORT).show();
                                    startPreview();
                                } else {
                                    CustomeWhiteBalanceBoolean = true;
                                    //implement seek bar here

                                    LayoutInflater WhiteBalanceInflater1 = LayoutInflater.from(Camera2VideoImageActivity.this);
                                    View WhiteBalance1 = WhiteBalanceInflater1.inflate(R.layout.whitebalance1_info_alertdialog, null);
                                    AlertDialog.Builder WhiteBalanceThing = new AlertDialog.Builder(Camera2VideoImageActivity.this);
                                    WhiteBalanceThing.setTitle("Colour inputs");
                                    WhiteBalanceThing.setView(WhiteBalance1);
                                    WhiteBalanceThing.setCancelable(true);
                                    mWhitebalance1 = (EditText) WhiteBalance1.findViewById(R.id.WhiteBalanceInputEditText1);
                                    mWhitebalance2 = (EditText) WhiteBalance1.findViewById(R.id.WhiteBalanceInputEditText2);
                                    mWhitebalance3 = (EditText) WhiteBalance1.findViewById(R.id.WhiteBalanceInputEditText3);
                                    mWhitebalance4 = (EditText) WhiteBalance1.findViewById(R.id.WhiteBalanceInputEditText4);
                                    WhiteBalanceThing.setNegativeButton("Close", new DialogInterface.OnClickListener() {


                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                                    WhiteBalanceThing.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            double mWhitebalance1temp = Double.parseDouble(mWhitebalance1.getText().toString());
                                            double mWhitebalance2temp = Double.parseDouble(mWhitebalance2.getText().toString());
                                            double mWhitebalance3temp = Double.parseDouble(mWhitebalance3.getText().toString());
                                            double mWhitebalance4temp = Double.parseDouble(mWhitebalance4.getText().toString());
                                            RggbChannelBlue = mWhitebalance4temp;
                                            RggbChannelG_even = mWhitebalance2temp;
                                            RggbChannelG_odd = mWhitebalance3temp;
                                            RggbChsnnelR = mWhitebalance1temp;
                                            startPreview();


                                        }
                                    });

                                    WhiteBalanceThing.show();
                                    //Toast.makeText(getApplicationContext(), "Color Correction Manual", Toast.LENGTH_SHORT).show();


                                }

                                //Slider Activation


                                break;


                            case R.id.WhiteBalanceCloudyDaylight:
                                if (!WhiteBalanceCloudyDaylightBoolean) {
                                    mWBMode = CONTROL_AWB_MODE_CLOUDY_DAYLIGHT;
                                    WhiteBalanceCloudyDaylightBoolean = true;
                                } else {
                                    WhiteBalanceAutoBoolean = true;
                                    mWBMode = CONTROL_AWB_MODE_AUTO;
                                    WhiteBalanceCloudyDaylightBoolean = false;


                                }
                                WhiteBalanceAutoBoolean = false;
                                WhiteBalanceIncandenscentBoolean = false;
                                WhiteBalanceWarmFluorescentBoolean = false;
                                WhiteBalanceTwilightBoolean = false;
                                WhiteBalanceShadeBoolean = false;
                                WhiteBalanceFluorescentBoolean = false;

                                WhiteBalanceDaylightBoolean = false;
                                SpotLockedWhiteBalanceBoolean = false;


                                startPreview();
                                break;
                            case R.id.WhiteBalanceDaylight:

                                if (!WhiteBalanceDaylightBoolean) {
                                    mWBMode = CONTROL_AWB_MODE_DAYLIGHT;
                                    WhiteBalanceDaylightBoolean = true;
                                } else {
                                    WhiteBalanceAutoBoolean = true;
                                    mWBMode = CONTROL_AWB_MODE_AUTO;


                                    WhiteBalanceDaylightBoolean = false;
                                }
                                WhiteBalanceAutoBoolean = false;
                                WhiteBalanceIncandenscentBoolean = false;
                                WhiteBalanceWarmFluorescentBoolean = false;
                                WhiteBalanceTwilightBoolean = false;
                                WhiteBalanceShadeBoolean = false;
                                WhiteBalanceFluorescentBoolean = false;
                                WhiteBalanceCloudyDaylightBoolean = false;
                                SpotLockedWhiteBalanceBoolean = false;


                                startPreview();
                                break;
                            case R.id.WhiteBalanceFluorescent:

                                if (!WhiteBalanceFluorescentBoolean) {

                                    WhiteBalanceFluorescentBoolean = true;
                                    mWBMode = CONTROL_AWB_MODE_FLUORESCENT;
                                    WhiteBalanceAutoBoolean = false;
                                    //put the rest
                                } else {
                                    WhiteBalanceFluorescentBoolean = false;
                                    mWBMode = CONTROL_AWB_MODE_AUTO;
                                    WhiteBalanceAutoBoolean = true;

                                }
                                WhiteBalanceAutoBoolean = false;
                                WhiteBalanceIncandenscentBoolean = false;
                                WhiteBalanceWarmFluorescentBoolean = false;
                                WhiteBalanceTwilightBoolean = false;
                                WhiteBalanceShadeBoolean = false;

                                WhiteBalanceCloudyDaylightBoolean = false;
                                WhiteBalanceDaylightBoolean = false;
                                SpotLockedWhiteBalanceBoolean = false;

                                startPreview();
                                break;
                            case R.id.WhiteBalanceShade:

                                if (!WhiteBalanceShadeBoolean) {
                                    WhiteBalanceShadeBoolean = true;
                                    mWBMode = CONTROL_AWB_MODE_SHADE;
                                } else {
                                    WhiteBalanceShadeBoolean = false;

                                    mWBMode = CONTROL_AWB_MODE_AUTO;
                                    WhiteBalanceAutoBoolean = true;
                                }
                                WhiteBalanceAutoBoolean = false;
                                WhiteBalanceIncandenscentBoolean = false;
                                WhiteBalanceWarmFluorescentBoolean = false;
                                WhiteBalanceTwilightBoolean = false;
                                WhiteBalanceFluorescentBoolean = false;
                                WhiteBalanceCloudyDaylightBoolean = false;
                                WhiteBalanceDaylightBoolean = false;
                                SpotLockedWhiteBalanceBoolean = false;

                                startPreview();
                                break;
                            case R.id.WhiteBalanceTwilight:
                                if (!WhiteBalanceTwilightBoolean) {
                                    mWBMode = CONTROL_AWB_MODE_TWILIGHT;
                                    WhiteBalanceTwilightBoolean = true;

                                } else {
                                    WhiteBalanceTwilightBoolean = false;
                                    mWBMode = CONTROL_AWB_MODE_AUTO;
                                    WhiteBalanceAutoBoolean = true;
                                }
                                WhiteBalanceIncandenscentBoolean = false;
                                WhiteBalanceWarmFluorescentBoolean = false;
                                WhiteBalanceAutoBoolean = false;
                                WhiteBalanceShadeBoolean = false;
                                WhiteBalanceFluorescentBoolean = false;
                                WhiteBalanceCloudyDaylightBoolean = false;
                                WhiteBalanceDaylightBoolean = false;
                                SpotLockedWhiteBalanceBoolean = false;


                                startPreview();
                                break;
                            case R.id.WhiteBalanceWarmFluorescent:
                                if (!WhiteBalanceWarmFluorescentBoolean) {
                                    mWBMode = CONTROL_AWB_MODE_WARM_FLUORESCENT;
                                    WhiteBalanceWarmFluorescentBoolean = true;

                                } else {
                                    WhiteBalanceWarmFluorescentBoolean = false;
                                    mWBMode = CONTROL_AWB_MODE_AUTO;
                                    WhiteBalanceAutoBoolean = true;
                                }
                                WhiteBalanceAutoBoolean = false;
                                WhiteBalanceIncandenscentBoolean = false;
                                WhiteBalanceTwilightBoolean = false;
                                WhiteBalanceShadeBoolean = false;
                                WhiteBalanceFluorescentBoolean = false;
                                WhiteBalanceCloudyDaylightBoolean = false;
                                WhiteBalanceDaylightBoolean = false;
                                SpotLockedWhiteBalanceBoolean = false;


                                startPreview();
                                break;
                            case R.id.WhiteBalanceIncandenscent:
                                if (!WhiteBalanceIncandenscentBoolean) {
                                    mWBMode = CONTROL_AWB_MODE_INCANDESCENT;
                                    WhiteBalanceIncandenscentBoolean = true;

                                } else {
                                    WhiteBalanceIncandenscentBoolean = false;
                                    mWBMode = CONTROL_AWB_MODE_AUTO;
                                    WhiteBalanceAutoBoolean = true;
                                }
                                WhiteBalanceAutoBoolean = false;
                                WhiteBalanceWarmFluorescentBoolean = false;
                                WhiteBalanceTwilightBoolean = false;
                                WhiteBalanceShadeBoolean = false;
                                WhiteBalanceFluorescentBoolean = false;
                                WhiteBalanceCloudyDaylightBoolean = false;
                                WhiteBalanceDaylightBoolean = false;
                                SpotLockedWhiteBalanceBoolean = false;


                                startPreview();
                                break;
                            case R.id.WhiteBalanceAuto:
                                if (mWBMode != CONTROL_AWB_MODE_AUTO) {
                                    mWBMode = CONTROL_AWB_MODE_AUTO;
                                    WhiteBalanceAutoBoolean = true;
                                    WhiteBalanceIncandenscentBoolean = false;
                                    WhiteBalanceWarmFluorescentBoolean = false;
                                    WhiteBalanceTwilightBoolean = false;
                                    WhiteBalanceShadeBoolean = false;
                                    WhiteBalanceFluorescentBoolean = false;
                                    WhiteBalanceCloudyDaylightBoolean = false;
                                    WhiteBalanceDaylightBoolean = false;
                                    SpotLockedWhiteBalanceBoolean = false;

                                } else {
                                    Toast.makeText(getApplicationContext(), "AUTO is already on", Toast.LENGTH_SHORT).show();

                                }
                                startPreview();
                                break;


                            case R.id.ChangeISO:
                                if (!ISOinputboolean) {
                                    ISOinputboolean = true;
                                } else {
                                    ISOinputboolean = false;
                                }


                                mISOtext.setVisibility(View.VISIBLE);
                                break;

                            case R.id.ISO100:

                                //Toast.makeText(getApplicationContext(), "100 ISO", Toast.LENGTH_SHORT).show();
                                ISOvalue = 100;
                                mISOtext.setText("ISO:" + ISOvalue);

                                startPreview();
                                break;
                            case R.id.ISO200:
                                ISOvalue = 200;

                                mISOtext.setText("ISO:" + ISOvalue);
                                startPreview();
                                break;
                            case R.id.ISO400:
                                ISOvalue = 400;
                                mISOtext.setText("ISO:" + ISOvalue);
                                startPreview();

                                //Toast.makeText(getApplicationContext(), "400 ISO", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.ISO800:
                                ISOvalue = 800;
                                mISOtext.setText("ISO:" + ISOvalue);
                                startPreview();

                                //Toast.makeText(getApplicationContext(), "800", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.ISO1600:
                                ISOvalue = 1600;
                                mISOtext.setText("ISO:" + ISOvalue);
                                startPreview();
                                //Toast.makeText(getApplicationContext(), "1600", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.customISO:
                                Toast.makeText(getApplicationContext(), "Custom ISO", Toast.LENGTH_SHORT).show();

                                mISOseekbar.setVisibility(View.VISIBLE);

                                mISOseekbar.setMax((int) HighestISO - LowestISO);

                                mISOseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                    @Override
                                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                        progress = ISOprogressValue;
                                    }

                                    @Override
                                    public void onStartTrackingTouch(SeekBar seekBar) {

                                    }

                                    @Override
                                    public void onStopTrackingTouch(SeekBar seekBar) {
                                        mISOtext.setText("ISO:" + (mISOseekbar.getProgress() + LowestISO));
                                        ISOseekProgress = (mISOseekbar.getProgress() + LowestISO);
                                        ISOvalue = ISOseekProgress;
                                        startPreview();

                                    }
                                });

                                startPreview();
                                break;
                            case R.id.custominputISO:


                                LayoutInflater inflater = LayoutInflater.from(Camera2VideoImageActivity.this);
                                final View subsubView = inflater.inflate(R.layout.manual_input_alertdialog, null);
                                final AlertDialog.Builder manualISODialog = new AlertDialog.Builder(Camera2VideoImageActivity.this);


                                mISOEditText = (EditText) subsubView.findViewById(R.id.isoEditText);
                                mISOEditTextView = (TextView) subsubView.findViewById(R.id.isoTitle);
                                mISOEditTextView.setText("ISO Range:" + LowestISO + "to" + HighestISO);
                                manualISODialog.setTitle("Manual ISO Input");
                                manualISODialog.setView(subsubView);
                                manualISODialog.setCancelable(true);
                                manualISODialog.setNegativeButton("Close", new DialogInterface.OnClickListener() {


                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                manualISODialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        int tempISO = Integer.parseInt(mISOEditText.getText().toString());
                                        if (tempISO <= HighestISO && tempISO >= LowestISO) {
                                            ISOvalue = tempISO;
                                            mISOtext.setText("ISO:" + ISOvalue);
                                            startPreview();
                                            return;
                                        } else {
                                            Toast.makeText(getApplicationContext(), "ISO value is out of range", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                manualISODialog.show();

                                break;
                            case R.id.ChangeShutterSpeedSeek:

                                mSeekbar.setVisibility(View.VISIBLE);
                                mSeekbar.setProgress(progressValue);

                                mTextSeekBar.setVisibility(View.VISIBLE);
                                if (ShutterSpeed2Double < 1) {
                                    mSeekbar.setMax((int) (ShutterSpeed2 - ShutterSpeed1));
                                } else {
                                    //Working on a precision bar for camera's with higher shutter speed capacity
                                    Toast.makeText(getApplicationContext(), "Precision Option Available", Toast.LENGTH_SHORT).show();
                                    mSeekBar2 = (SeekBar) findViewById(R.id.seekBar2);
                                    mSeekBar2.setVisibility(View.VISIBLE);
                                    mSeekbar.setMax((int) Math.round(ShutterSpeed2Double));
                                    mTextSeekBar.setText("Shutter Speed(in s)");
                                }

                                //Note:The SeekBar can only take Interger Values. If ShutterSpeed2-ShutterSpeed1==0 then the ShutterSpeed difference is too great
                                //Integers can
                                //mSeekbar.setProgress(100000);

                                mMinimumShutterSpeed.setVisibility(View.VISIBLE);
                                mMinimumShutterSpeed.setText(ShutterSpeed1String);

                                mMaximumShutterSpeed.setVisibility(View.VISIBLE);
                                mMaximumShutterSpeed.setText(ShutterSpeed2String);
                                mSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                                    @Override
                                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                                        progress = progressValue;


                                    }

                                    @Override
                                    public void onStartTrackingTouch(SeekBar seekBar) {
                                        //Toast.makeText(getApplicationContext(), "Start", Toast.LENGTH_SHORT).show();

                                    }

                                    @Override
                                    public void onStopTrackingTouch(SeekBar seekBar) {
                                        mTextSeekBar.setText("Shutter Speed(in ns):" + (mSeekbar.getProgress() + ShutterSpeed1) + "/" + Math.round(mSeekbar.getMax() + ShutterSpeed1));
                                        Toast.makeText(getApplicationContext(), "Setting Shutter Speed", Toast.LENGTH_SHORT).show();
                                        ShutterSpeedValue = (mSeekbar.getProgress() + ShutterSpeed1);

                                        if(AutoNumber !=1){
                                            AutoNumber=1;
                                        }
                                        startPreview();
                                    }
                                });


                                break;
                            case R.id.ChangeShutterSpeedInput:


                                LayoutInflater inflater3 = LayoutInflater.from(Camera2VideoImageActivity.this);
                                final View ChangeShutterSpeedView = inflater3.inflate(R.layout.shutterspeed_input_alertdialog, null);
                                final AlertDialog.Builder manualShutterSpeedDialog = new AlertDialog.Builder(Camera2VideoImageActivity.this);
                                mShutterSpeedEditText = (EditText) ChangeShutterSpeedView.findViewById(R.id.ShutterSpeedEditText);
                                mShutterSpeedEditTextView = (TextView) ChangeShutterSpeedView.findViewById(R.id.ShutterSpeedTitle);
                                mShutterSpeedEditTextView.setText("ShutterSpeed Range: " + ShutterSpeed1 + " to " + ShutterSpeed2);
                                manualShutterSpeedDialog.setTitle("Manual Shutter Speed Input");
                                manualShutterSpeedDialog.setView(ChangeShutterSpeedView);
                                manualShutterSpeedDialog.setCancelable(true);
                                manualShutterSpeedDialog.setNegativeButton("Close", new DialogInterface.OnClickListener() {


                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });


                                manualShutterSpeedDialog.setPositiveButton("Confirm ", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        int tempShutterSpeed = Integer.parseInt(mShutterSpeedEditText.getText().toString());
                                        if (tempShutterSpeed <= ShutterSpeed2 && tempShutterSpeed >= ShutterSpeed1) {
                                            ShutterSpeedValue = tempShutterSpeed;
                                            //.setText("ISO:"+ ISOvalue);
                                            startPreview();
                                            return;
                                        } else {
                                            Toast.makeText(getApplicationContext(), "ShutterSpeed value is out of range", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                manualShutterSpeedDialog.show();

                                break;
                            case R.id.ChangeShutterSpeedInput2:
                                LayoutInflater inflater4 = LayoutInflater.from(Camera2VideoImageActivity.this);
                                final View ChangeShutterSpeedView2 = inflater4.inflate(R.layout.shutterspeed_input_alertdialog2, null);
                                final AlertDialog.Builder manualShutterSpeedDialog2 = new AlertDialog.Builder(Camera2VideoImageActivity.this);
                                mShutterSpeedEditText2 = (EditText) ChangeShutterSpeedView2.findViewById(R.id.ShutterSpeedEditText2);
                                mShutterSpeedEditTextView2 = (TextView) ChangeShutterSpeedView2.findViewById(R.id.ShutterSpeedTitle2);
                                mShutterSpeedEditTextView2.setText("ShutterSpeed Range: " + ShutterSpeed1String + " to " + ShutterSpeed2String);
                                manualShutterSpeedDialog2.setTitle("Manual Shutter Speed Input");
                                manualShutterSpeedDialog2.setView(ChangeShutterSpeedView2);
                                manualShutterSpeedDialog2.setCancelable(true);
                                manualShutterSpeedDialog2.setNegativeButton("Close", new DialogInterface.OnClickListener() {


                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });


                                manualShutterSpeedDialog2.setPositiveButton("Confirm ", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String tempShutterSpeedString[] = new String[2];
                                        if(AutoNumber !=1){
                                            AutoNumber=1;
                                        }
                                        String tt;
                                        tt = mShutterSpeedEditText2.getText().toString();
                                        //make regex if statement here to satify numbers numbers greater than 1
                                        tempShutterSpeedString = tt.split("/");
                                        double tempShutterSpeed1 = Double.parseDouble(tempShutterSpeedString[0]);
                                        double tempShutterSpeed2 = Double.parseDouble(tempShutterSpeedString[1]);

                                        double tempShutterSpeed = ((tempShutterSpeed1 / tempShutterSpeed2) * 1000000000);
                                        if (tempShutterSpeed <= ShutterSpeed2 && tempShutterSpeed >= ShutterSpeed1) {
                                            ShutterSpeedValue = (long) tempShutterSpeed;
                                            //.setText("ISO:"+ ISOvalue);
                                            startPreview();
                                            return;
                                        } else {
                                            Toast.makeText(getApplicationContext(), "ShutterSpeed value is out of range", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                manualShutterSpeedDialog2.show();


                                break;

                            case R.id.getCameraInfo:
                                LayoutInflater inflater2 = LayoutInflater.from(Camera2VideoImageActivity.this);
                                View cameraInfoSubView = inflater2.inflate(R.layout.camera_info_alertdialog, null);



                                mCameraInfoTextView5 = (TextView) cameraInfoSubView.findViewById(R.id.MoreInfo);
                                String newText ="";
                                String newText2="";
                                String newText3="";
                                String newText4="";
                                StreamConfigurationMap scmap = mCameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                                previewSizes = scmap.getOutputSizes(ImageFormat.JPEG);

                                for (int i = 0; i < previewSizes.length; i++) {

                                    newText= newText+" , " + previewSizes[i] + ""; // can manipulate using substring also

                                }
                                for (int i = 0; i < mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AVAILABLE_SCENE_MODES).length; i++) {

                                    newText2 =  newText2 + "" + mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AVAILABLE_SCENE_MODES)[i] + " , ";

                                }

                                for (int i = 0; i < mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AVAILABLE_EFFECTS).length; i++) {

                                     newText3= newText3 + "" + mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AVAILABLE_EFFECTS)[i] + " , ";

                                }

                                for (int i = 0; i < mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AWB_AVAILABLE_MODES).length; i++) {

                                    newText4 = newText4 + " " + mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AWB_AVAILABLE_MODES)[i] + " ";
                                    //AWBArr[i]=mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AWB_AVAILABLE_MODES)[i];

                                }
                                mCameraInfoTextView5.setMovementMethod(new ScrollingMovementMethod());

                                mCameraInfoTextView5.setText("Shutter Speed Information(in s):" + ShutterSpeed1String + "-" + ShutterSpeed2String + "\n" + "ISO Range:" + mCameraCharacteristics.get(mCameraCharacteristics.SENSOR_INFO_SENSITIVITY_RANGE)
                                        + "\n" + "White Level:" + mCameraCharacteristics.get(mCameraCharacteristics.SENSOR_INFO_WHITE_LEVEL) + "\n" + "Sensor Physical Size: " + mCameraCharacteristics.get(mCameraCharacteristics.SENSOR_INFO_PHYSICAL_SIZE)
                                        + "\n" + "Sensor Max Analog Sensitivity:" + mCameraCharacteristics.get(mCameraCharacteristics.SENSOR_MAX_ANALOG_SENSITIVITY)
                                        + "\n" + "Standard reference illuminant:" + mCameraCharacteristics.get(mCameraCharacteristics.SENSOR_REFERENCE_ILLUMINANT1)
                                        + "\n" + "Camera Compensation Range:" + mCameraCharacteristics.get(mCameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE)
                                        + "\n" + "Flash Available: " + mCameraCharacteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE)
                                        + "\n" + "Supported Available Burst Capabilities:" + contains(mCameraCharacteristics.get(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES), CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES_BURST_CAPTURE)
                                        + "\n" + "SENSOR_COLOR_TRANSFORM_1: " + mCameraCharacteristics.get(mCameraCharacteristics.SENSOR_COLOR_TRANSFORM1)
                                        + "\n" + "SENSOR_COLOR_TRANSFORM_2: " + mCameraCharacteristics.get(mCameraCharacteristics.SENSOR_COLOR_TRANSFORM2)
                                        + "\n" + "FORWARD_MATRIX_1: " + mCameraCharacteristics.get(mCameraCharacteristics.SENSOR_FORWARD_MATRIX1)
                                        + "\n" + "FORWARD_MATRIX_2: " + mCameraCharacteristics.get(mCameraCharacteristics.SENSOR_FORWARD_MATRIX2)
                                        + "\n" + "Camera Aperature: "+mCurrentAperatureValue
                                        + "\n" + "Control_AE_Compensation_Range: "+mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE)
                                        +"\n"+ "Control_AE_Compensation_Step: "+ mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP)
                                        +"\n"+ "Noise Reduction Modes: "+NoiseReductionModeString
                                        +"\n"+ "Bayer Arrangement: "+ SensorinfoColorfiltering
                                        +"\n"+ "Pipeline Max Depth: "+ mCameraCharacteristics.get(mCameraCharacteristics.REQUEST_PIPELINE_MAX_DEPTH)
                                        +"\n"+"Tonemap Max Curve Points"+ mCameraCharacteristics.get(mCameraCharacteristics.TONEMAP_MAX_CURVE_POINTS)
                                        +"\n"+"Len Available Aperature: "+mCameraCharacteristics.get(mCameraCharacteristics.LENS_INFO_AVAILABLE_APERTURES)
                                        +"\n"+"Resolutions: " + newText
                                        +"\n"+"Supported Scenes: "+ newText2
                                        +"\n"+"Supported Effects: " + newText3
                                        +"\n"+"Supported Face Detection: "+ newText4

                                        + "\n" + "Supported Auto White Balances"

                                );



                                






                                AlertDialog.Builder builder = new AlertDialog.Builder(Camera2VideoImageActivity.this);
                                builder.setView(cameraInfoSubView);
                                builder.setTitle("Camera Information");
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });




                                AlertDialog alertDialog2 = builder.create();
                                alertDialog2.show();
                                break;


                            case R.id.devButton:
                                //ChangeWhiteBalanceSpotRawOn = true;
                                wbThreadisEnabled = !wbThreadisEnabled;
                                isAdjustingWB2=false;
                                //startPreview();
                                break;
                            case R.id.devButton2:
                                for(int j=0;j<rawHeight;j++){
                                    for(int i=0;i<rawWidth;i++){
                                        pixelData=pixelData+""+mMat2.get(j,i).toString();
                                    }
                                }
                                generateNoteonSD(Camera2VideoImageActivity.this,"pixelData",pixelData);
                                break;
                            case R.id.devButton3:
                                CaptureandConvertRAWtoPNG();

                                break;



                            default:
                                return false;
                        }
                        return true;
                    }

                });
                popupMenu.show();

            }

        });


        mStillImageButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                try {
                    checkJPEGWriteStoragePermission();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (ExportasRGBasTextboolean){
                    Toast.makeText(Camera2VideoImageActivity.this, "Read RGB and export", Toast.LENGTH_SHORT).show();
                    writeRGBPictureasText();
                if(ExportAsRGGBasTextboolean){
                    Toast.makeText(Camera2VideoImageActivity.this, "Read RGGB and export", Toast.LENGTH_SHORT).show();
                }

                }

                mStillImageButton.setImageResource(R.mipmap.campic);
                if (!mBurstOn) {
                    if(Capture_JPEG || RawwithJPEg){

                        sound.playHitSound();
                        lockFocus();

                    }




                    //startStillCaptureRequest();
                }
                if (mBurstOn) {
                    //Toast.makeText(getApplicationContext(), "Burst Done", Toast.LENGTH_SHORT).show();
                    mBurstOn = false;
                }
                if (mChronometer.getVisibility() == View.VISIBLE) {
                    mTimeInterval.setVisibility(View.INVISIBLE);
                    mChronometer.stop();
                    mChronometer.setVisibility(View.INVISIBLE);

                }
            }
        });
        mStillImageButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mStillImageButton.setImageResource(R.mipmap.btn_timelapse);
                mTimeInterval.setVisibility(View.VISIBLE);
                mTimeInterval.setText("Second Step:" + SecondStep);


                mBurstOn = true;
                Toast.makeText(getApplicationContext(), "Burst Started", Toast.LENGTH_SHORT).show();
                mChronometer.setBase(SystemClock.elapsedRealtime());
                mChronometer.setVisibility(View.VISIBLE);
                mChronometer.start();
                mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                    @Override
                    public void onChronometerTick(Chronometer chronometer) {
                        ChronoCount = ChronoCount + 1;


                        //chronometer.refreshDrawableState();
                        if (mPhotoTimeLimitNumber == 1) {


                            if (ChronoCount % SecondStep == 0) {
                                lockFocus();
                            }

                        } else if (mPhotoTimeLimitNumber == 0) {
                            if (ChronoCount == (PhotoBurstTimeStop)) {
                                if (ChronoCount % SecondStep == 0) {
                                    lockFocus();
                                }

                                mChronometer.stop();
                                mChronometer.setVisibility(View.INVISIBLE);
                                mStillImageButton.setImageResource(R.mipmap.campic);
                            } else {
                                if (ChronoCount % SecondStep == 0) {
                                    lockFocus();
                                }
                            }


                        }

                    }
                });


                //mStillImageButton.setImageResource(R.mipmap.campic);


                return true;
            }
        });


        mRecordImageButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (mIsRecording || mIsTimelapse) {
                    mChronometer.stop();
                    mChronometer.setVisibility(View.INVISIBLE);
                    mIsRecording = false;
                    mIsTimelapse = false;
                    mRecordImageButton.setImageResource(R.mipmap.vidpiconline);
                    mTimeInterval.setVisibility(View.INVISIBLE);
                    mMediaRecorder.stop();
                    mMediaRecorder.reset();
                    Intent mediaStoreUpdateIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    mediaStoreUpdateIntent.setData(Uri.fromFile(new File(mVideoFileName)));
                    sendBroadcast(mediaStoreUpdateIntent);


                    startPreview();
                } else {
                    //mIsRecording = true;


                    mRecordImageButton.setImageResource(R.mipmap.vidpicbusy);
                    try {
                        checkWriteStoragePermission();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

        });
        mRecordImageButton.setOnLongClickListener(new View.OnLongClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onLongClick(View v) {
                mIsTimelapse = true;
                mTimeInterval.setVisibility(View.VISIBLE);
                mTimeInterval.setText("Pictures per Second: " + VideoTimelapsSecondStep);
                try {
                    checkWriteStoragePermission();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;

            }
        });


        //end of onCreate





    }

    private void generateNoteonSD(Camera2VideoImageActivity context, String sFileName, String sBody) {
    try{
        File root=new File(Environment.getExternalStorageDirectory(),"Notes");
        if(!root.exists()){
            root.mkdirs();
        }
        File gpxfile=new File(root,sFileName);
        FileWriter writer= new FileWriter(gpxfile);
        writer.append(sBody);
        writer.flush();
        writer.close();
        Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
    }catch (IOException e){
        e.printStackTrace();
    }

    }


    private void writeRGBPictureasText() {
        if(!txtfolder.exists()){
            txtfolder.mkdirs();
        }

            String txttimestamp=new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String prepend="TXT_"+txttimestamp+"_";
            File txtfile2= null;
            try {
                txtfile2 = File.createTempFile(prepend,".txt",txtfolder);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String mtxtFileName2=txtfile2.getAbsolutePath();
            try {
                FileOutputStream newfileoutstream2=new FileOutputStream(mtxtFileName2);
                String Redstring="";
                String Bluestring="";
                String Greenstring="";
                Bitmap newBitmap=mTextureView.getBitmap();

                for(int i=0;i<mTextureView.getWidth();i++){
                    for(int j=0;j<mTextureView.getHeight();j++){
                         int NewPixel= newBitmap.getPixel(i,j);

                        //RedPixelValues2[i][j]=Color.red(NewPixel);
                        Redstring=(Redstring+Color.red(NewPixel)+", ");
                        //GreenPixelValues2[i][j]=Color.green(NewPixel);
                        Greenstring=(Greenstring+Color.green(NewPixel)+", ");

                        //BluePixelValues2[i][j]=Color.blue(NewPixel);
                        Bluestring=(Bluestring+Color.blue(NewPixel)+", ");


                    }
                }


                byte[] txt2bytes=(("Red: "+Redstring)+("Green: "+Greenstring)+("Blue: "+Bluestring)).getBytes();
                newfileoutstream2.write(txt2bytes);

            } catch (IOException e) {
                e.printStackTrace();
            }



    }

    /*public void ModifyXMLFile2(){


        try {
            String filepath="C:/Users/mstew/AndroidStudioProjects/Camera2VideoImage/app/src/main/res/values/strings.xml";
            DocumentBuilderFactory docFacotry=DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder=docFacotry.newDocumentBuilder();
            File newfile=new File(filepath);

            scannedfilestring= "file found";
            Scanner filescanner=new Scanner(newfile);
            scannedfilestring= String.valueOf(filescanner.hasNext());
            filescanner.close();

            //Document doc =docBuilder.parse(newfile);





            //Document doc= docBuilder.parse(filepath);






        } catch (Exception e) {
            e.printStackTrace();
            //scannedfilestring="catched";
        }


    }*/


    //pt9
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION_RESULT) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "App will not run without camera services", Toast.LENGTH_SHORT).show();
            }

            if (grantResults[1] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "App will not run without audio services", Toast.LENGTH_SHORT).show();

            }
        }
        if (requestCode == REQUEST_WRITE_EXTERNAL_STORAGE_PERMISSION_RESULT) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mIsRecording = true;
                mRecordImageButton.setImageResource(R.mipmap.vidpiconline);
                try {
                    createVideoFileName();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "Permission granted!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "App needs to save video to run", Toast.LENGTH_SHORT).show();

            }
        }
    }


    private CaptureRequest.Builder mCaptureRequestBuilder;

    private void startPreview() {




        SurfaceTexture surfaceTexture = mTextureView.getSurfaceTexture();

        StreamConfigurationMap scmap = mCameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        previewSizes = scmap.getOutputSizes(ImageFormat.JPEG);
        if(previewinit){
            for (int i = 0; i < previewSizes.length; i++) {
                arraylist.add(i,previewSizes[i]);

            }
            previewinit=false;

        }
        if(RefreshBoolean){

        }


        //uh start things here?


        surfaceTexture.setDefaultBufferSize(mPreviewSize.getWidth(), mPreviewSize.getHeight());
        Surface previewSurface = new Surface(surfaceTexture);





        try {

            //setSettings();



            mCaptureRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            mCaptureRequestBuilder.set(CaptureRequest.CONTROL_EFFECT_MODE, mCameraEffect);
            mCaptureRequestBuilder.addTarget(previewSurface);
            mCaptureRequestBuilder.set(CaptureRequest.NOISE_REDUCTION_MODE,NoiseReductionModesint);
            mCaptureRequestBuilder.set(CaptureRequest.SENSOR_TEST_PATTERN_MODE,PatternTestint);
            mCaptureRequestBuilder.set(CaptureRequest.SENSOR_TEST_PATTERN_DATA,new int[]{0,0,255,255});
            mCaptureRequestBuilder.set(CaptureRequest.EDGE_MODE,EdgeMode);
            mCaptureRequestBuilder.set(CaptureRequest.HOT_PIXEL_MODE,HotPixelMode);
            mCaptureRequestBuilder.set(CaptureRequest.JPEG_QUALITY,(byte)JPEGQuality);
            mCaptureRequestBuilder.set(CaptureRequest.TONEMAP_MODE,ToneMapMode);
            if(ToneMapMode==0){
                //mCaptureRequestBuilder.set(CaptureRequest.TONEMAP_CURVE,)
                //input special custome curve code here
            }
            if(ToneMapMode==3){
                //mCaptureRequestBuilder.set(CaptureRequest.TONEMAP_GAMMA,...)
                //input gaamma value curve here
            }
            if(ToneMapMode==4){
                //mCaptureRequestBuilder.set(CaptureRequest.TONEMAP_PRESET_CURVE,...)
                //input present curve code here
            }


            if(ExposureCompensationSeekBarboolean){
                //fill in here
                //mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE,CaptureRequest.CONTROL_AE_MODE_ON);
                mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION, (int) (ExposureCompensationIntegerProgress-mCameraCharacteristics.get(mCameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE).getUpper()));
                //mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AE_LOCK,true);
                //Added more Exposure Compensation Features



            }
            mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AE_ANTIBANDING_MODE,AntiBandingModeint);

            if (supports_face_detection_mode_simple && isSupports_face_detection_mode_full == false) {
                mCaptureRequestBuilder.set(CaptureRequest.STATISTICS_FACE_DETECT_MODE, CaptureRequest.STATISTICS_FACE_DETECT_MODE_SIMPLE);
            }
            if (isSupports_face_detection_mode_full) {
                mCaptureRequestBuilder.set(CaptureRequest.STATISTICS_FACE_DETECT_MODE, CaptureRequest.STATISTICS_FACE_DETECT_MODE_FULL);
            }

            if (AutoNumber == 0) {
                //AutoSettings
                mCaptureRequestBuilder.set(CaptureRequest.CONTROL_MODE, CaptureRequest.CONTROL_MODE_AUTO);
                mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, 1);
            }



            if (CustomeWhiteBalanceBoolean) {
                mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AWB_MODE, CaptureRequest.CONTROL_AWB_MODE_OFF);
                mCaptureRequestBuilder.set(CaptureRequest.COLOR_CORRECTION_GAINS, new RggbChannelVector((float) RggbChsnnelR, (float) RggbChannelG_even, (float) RggbChannelG_odd, (float) RggbChannelBlue));
            } else if (!CustomeWhiteBalanceBoolean && mWBMode !=-1) {
                mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AWB_MODE, mWBMode);
            }
            if(mWBMode == CONTROL_AWB_MODE_AUTO && ColorSpaceInputBoolean==false){
                mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AWB_MODE, CaptureRequest.CONTROL_AWB_MODE_AUTO);
            }else if(mWBMode == -1 && ColorSpaceInputBoolean == false){
                mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AWB_MODE, CaptureRequest.CONTROL_AWB_MODE_OFF);
                RggbChannelVector UNIT_GAIN = new RggbChannelVector(mVectorR, mVectorG_EVEN, mVectorG_ODD, mVectorB);
                mCaptureRequestBuilder.set(CaptureRequest.COLOR_CORRECTION_GAINS, UNIT_GAIN);
            }
            else if(mWBMode !=-1 && ColorSpaceInputBoolean==true) {
                mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AWB_MODE, CameraMetadata.CONTROL_AWB_MODE_OFF);
                mCaptureRequestBuilder.set(CaptureRequest.COLOR_CORRECTION_MODE, CaptureRequest.COLOR_CORRECTION_MODE_TRANSFORM_MATRIX);
                mCaptureRequestBuilder.set(CaptureRequest.COLOR_CORRECTION_TRANSFORM, new ColorSpaceTransform(new int[]{
                        ColorSpaceRed1, 256, ColorSpaceRed2, 256, ColorSpaceRed3, 256,
                        ColorSpaceGreen1, 256, ColorSpaceGreen2, 256, ColorSpaceGreen3, 256,
                        ColorSpaceBlue1, 256, ColorSpaceBlue2, 256, ColorSpaceBlue3, 256
                }));
            }




            else if (mWBMode == -1 && ColorSpaceInputBoolean==true) {
                mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AWB_MODE, CaptureRequest.CONTROL_AWB_MODE_OFF);
                RggbChannelVector UNIT_GAIN = new RggbChannelVector(mVectorR, mVectorG_EVEN, mVectorG_ODD, mVectorB);

                mCaptureRequestBuilder.set(CaptureRequest.COLOR_CORRECTION_MODE, CaptureRequest.COLOR_CORRECTION_MODE_TRANSFORM_MATRIX);
                mCaptureRequestBuilder.set(CaptureRequest.COLOR_CORRECTION_GAINS, UNIT_GAIN);


                mCaptureRequestBuilder.set(CaptureRequest.COLOR_CORRECTION_TRANSFORM, new ColorSpaceTransform(new int[]{
                        ColorSpaceRed1, 256, ColorSpaceRed2, 256, ColorSpaceRed3, 256,
                        ColorSpaceGreen1, 256, ColorSpaceGreen2, 256, ColorSpaceGreen3, 256,
                        ColorSpaceBlue1, 256, ColorSpaceBlue2, 256, ColorSpaceBlue3, 256
                }));


            }
            if (BooleanOpticalStabilizationOn) {
                mCaptureRequestBuilder.set(CaptureRequest.LENS_OPTICAL_STABILIZATION_MODE, CaptureRequest.LENS_OPTICAL_STABILIZATION_MODE_ON);
            } else if (!BooleanOpticalStabilizationOn) {
                mCaptureRequestBuilder.set(CaptureRequest.LENS_OPTICAL_STABILIZATION_MODE, CaptureRequest.LENS_OPTICAL_STABILIZATION_MODE_OFF);
            }
            if (manualFocusEnableIsChecked) {
                mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_OFF);
                mCaptureRequestBuilder.set(CaptureRequest.LENS_FOCUS_DISTANCE, (float) ((float) 1 / (float) mFocusDistance));
                //Toast.makeText(getApplicationContext(), "CONTROL AF OFF", Toast.LENGTH_SHORT).show();
                mFocusDistanceMem = (float) mFocusDistance;
            }
            if (!manualFocusEnableIsChecked && lockFocusEnableIsChecked) {
                mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CameraMetadata.CONTROL_AF_MODE_OFF);
                mCaptureRequestBuilder.set(CaptureRequest.LENS_FOCUS_DISTANCE, (float) mCurrentFocusDistance);
                mFocusDistanceMem = (float) mFocusDistance;
            }
            if (!manualFocusEnableIsChecked && !lockFocusEnableIsChecked) {
                {
                    mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER_CANCEL);
                    mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AF_MODE_AUTO);
                }
            }
            if (!AutoWhiteBalancelockBoolean && !CustomeWhiteBalanceBoolean && mWBMode!=-1) {
                mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AWB_LOCK, false);
                mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AWB_MODE, mWBMode);
            }
            if (AutoWhiteBalancelockBoolean) {
                mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AWB_MODE, CONTROL_AWB_MODE_AUTO);
                mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AWB_LOCK, true);
                //mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AWB_MODE, CaptureRequest.CONTROL_AWB_MODE_OFF);
            }

            if (AutoNumber == 2) {

                //mCaptureRequestBuilder.set(CaptureRequest.CONTROL_MODE,CaptureRequest.CONTROL_MODE_OFF);
                mCaptureRequestBuilder.set(CaptureRequest.CONTROL_MODE, CaptureRequest.CONTROL_MODE_USE_SCENE_MODE);
                mCaptureRequestBuilder.set(CaptureRequest.CONTROL_SCENE_MODE, mSceneMode);
                Toast.makeText(getApplicationContext(), "mSceneModeNumber:" + mSceneMode, Toast.LENGTH_SHORT).show();

            } /*else if (AutoNumber == 3) {
                mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_OFF);
                mCaptureRequestBuilder.set(CaptureRequest.SENSOR_EXPOSURE_TIME, ShutterSpeedValue);
                mCaptureRequestBuilder.set(CaptureRequest.SENSOR_SENSITIVITY, ISOvalue);
                mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AWB_MODE, mWBMode);
                mCaptureRequestBuilder.set(CaptureRequest.CONTROL_EFFECT_MODE,mCameraEffect);
                //mCaptureRequestBuilder.set(CaptureRequest.SENSOR_EXPOSURE_TIME, CaptureRequest )
            }*/
            if (mFlashMode == 0) {
                mCaptureRequestBuilder.set(CaptureRequest.FLASH_MODE, FLASH_MODE_OFF);
            }
            if (mFlashMode == 3) {
                mCaptureRequestBuilder.set(CaptureRequest.FLASH_MODE, FLASH_MODE_TORCH);
            }
            if (AutoNumber == 1) {
                mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_OFF);
                mCaptureRequestBuilder.set(CaptureRequest.SENSOR_EXPOSURE_TIME, ShutterSpeedValue);
                mCaptureRequestBuilder.set(CaptureRequest.SENSOR_SENSITIVITY, ISOvalue);
            }




            final CameraCaptureSession.CaptureCallback PreCaptureCall = new CameraCaptureSession.CaptureCallback() {
                @Override
                public void onCaptureCompleted(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull TotalCaptureResult result) {
                    super.onCaptureCompleted(session, request, result);
                    mCurrentFocusDistance = result.get(CaptureResult.LENS_FOCUS_DISTANCE);
                    mCurrentISOValue = result.get(CaptureResult.SENSOR_SENSITIVITY);
                    mCurrentSSvalue = result.get(CaptureResult.SENSOR_EXPOSURE_TIME);
                    mCurrentAperatureValue = result.get(CaptureResult.LENS_APERTURE);
                    Integer mode = result.get(CaptureResult.STATISTICS_FACE_DETECT_MODE);
                     faces = result.get(CaptureResult.STATISTICS_FACES);

                    if(trackfacesbool && faces.length>=1){


                        leftface=(((float) faces[0].getBounds().left/(float)mSensorInfoActiveArraySize.getWidth()))*(float)Size1.getWidth();
                        topface=(((float)faces[0].getBounds().top/(float)mSensorInfoActiveArraySize.getHeight()))*(float)Size1.getHeight();
                        rightface=(((float)faces[0].getBounds().right/(float)mSensorInfoActiveArraySize.getWidth()))*(float)Size1.getWidth();
                        bottomface=(((float)faces[0].getBounds().bottom/(float)mSensorInfoActiveArraySize.getHeight()))*(float)Size1.getHeight();
                        starttrackingbool=true;


                        //implement face detector with textureview bitmap


                        //BallInspectorx=(int)(((float)faces[0].getBounds().centerX()/(float) mSensorInfoActiveArraySize.width())*mTextureView.getWidth());
                        //BallInspectory=(int)(((float)faces[0].getBounds().centerY()/(float)mSensorInfoActiveArraySize.height())*mTextureView.getHeight());
                    }else{
                        starttrackingbool=false;
                    }

                    rggbChannelVector = result.get(CaptureResult.COLOR_CORRECTION_GAINS);
                    ColorCorrectionTransform = result.get(CaptureResult.COLOR_CORRECTION_TRANSFORM);
                    mNumberofFaces = faces.length;

                    //Toast.makeText(getApplicationContext(), ""+counter, Toast.LENGTH_SHORT).show();


                }
            };


            mCameraDevice.createCaptureSession(Arrays.asList(previewSurface, mImageReader.getSurface(), mRawImageReader.getSurface()),
                    new CameraCaptureSession.StateCallback() {

                        @Override
                        public void onConfigured(CameraCaptureSession session) {

                            mPreviewCaptureSession = session;

                            try {
                                if (supports_face_detection_mode_simple) {
                                    if (isSupports_face_detection_mode_full) {
                                        mCaptureRequestBuilder.set(CaptureRequest.STATISTICS_FACE_DETECT_MODE, CameraMetadata.STATISTICS_FACE_DETECT_MODE_FULL);
                                    } else {
                                        mCaptureRequestBuilder.set(CaptureRequest.STATISTICS_FACE_DETECT_MODE, CameraMetadata.STATISTICS_FACE_DETECT_MODE_SIMPLE);
                                    }
                                }

                                mPreviewCaptureSession.setRepeatingRequest(mCaptureRequestBuilder.build(),
                                        PreCaptureCall, mBackgroundHandler);

                            } catch (CameraAccessException e) {
                                Log.d(TAG,"Acces DEnied");
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onConfigureFailed(CameraCaptureSession session) {
                            Toast.makeText(getApplicationContext(), "Unable to set up camera preview", Toast.LENGTH_SHORT).show();

                        }
                    }, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        mMinFocusDistance = mCameraCharacteristics.get(CameraCharacteristics.LENS_INFO_MINIMUM_FOCUS_DISTANCE);
        mMaxFocusDistance = mCameraCharacteristics.get(CameraCharacteristics.LENS_INFO_HYPERFOCAL_DISTANCE);
        //mChangeFocusSeekBar.setMax((int) ((int) (1/mMaxFocusDistance - 1/mMinFocusDistance)/0.05));
    }

    @Override
    protected void onPause() {

        sm.unregisterListener(this);
        closeCamera();
        stopBackgroundThread();
        super.onPause();


    }

    //Here we are going to create a video busy button
    private ImageButton mRecordImageButton;
    private ImageButton mSettingsButton;
    private boolean mIsRecording = false;
    //Setting up storage
    private File mVideoFolder;
    private String mVideoFileName;

    //creating the video folder
    private void createVideoFolder() {
        File movieFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
        mVideoFolder = new File(movieFile, "Camera2_Video_Image");
        //check to see if the folder is already created
        if (!mVideoFolder.exists()) {
            mVideoFolder.mkdirs();

        }

    }


//now we have to call the videoFolder onCreate

    private File createVideoFileName() throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        //there are two types of SimpleDateFormat and Date()
        String prepend = "VIDEO_" + timestamp + "_";
        File videoFile = File.createTempFile(prepend, ".mp4", mVideoFolder);
        mVideoFileName = videoFile.getAbsolutePath();
        return videoFile;

    }

    //create and Initialize REQUEST_WRITE_EXTERNAL_STORAGE_PERMISSION_RESULT
    private static final int REQUEST_WRITE_EXTERNAL_STORAGE_PERMISSION_RESULT = 1;


    //Now we have to make this marshmellow compatable
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkWriteStoragePermission() throws IOException {
        if (mIsTimelapse) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


                if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    mIsTimelapse = true;
                    mRecordImageButton.setImageResource(R.mipmap.btn_timelapse);
                    createVideoFileName();
                    startRecord();
                    mMediaRecorder.start();
                    //Toast.makeText(getApplicationContext(), "Recording Timelapse", Toast.LENGTH_SHORT).show();
                    mChronometer.setBase(SystemClock.elapsedRealtime());
                    mChronometer.setVisibility(View.VISIBLE);
                    mChronometer.start();
                    mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                        @Override
                        public void onChronometerTick(Chronometer chronometer) {
                            mChronoTick++;
                            if(mVideoTimeLimitNumber==0){

                                if(mChronoTick==TempVideoTimeLimit){
                                    mChronometer.stop();
                                    mMediaRecorder.stop();
                                    mChronometer.setVisibility(View.INVISIBLE);
                                    mMediaRecorder.reset();
                                    mRecordImageButton.setImageResource(R.mipmap.vidpiconline);
                                    mIsTimelapse=false;
                                    startPreview();
                                }

                            }

                        }
                    });


                } else {
                    if (shouldShowRequestPermissionRationale(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        Toast.makeText(this, "app needs to be able to save videos", Toast.LENGTH_SHORT).show();

                    }
                    requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_STORAGE_PERMISSION_RESULT);
                }
            } else {
                mIsRecording = true;
                mRecordImageButton.setImageResource(R.mipmap.btn_timelapse);
                try {
                    createVideoFileName();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                startRecord();
                mMediaRecorder.start();
                mChronometer.setBase(SystemClock.elapsedRealtime());
                mChronometer.setVisibility(View.VISIBLE);
                mChronometer.start();






            }

        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {


                    mIsRecording = true;
                    mRecordImageButton.setImageResource(R.mipmap.vidpicbusy);

                    try {
                        createVideoFileName();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    startRecord();

                    mMediaRecorder.start();
                    Toast.makeText(this, "Recording Video", Toast.LENGTH_SHORT).show();
                    mChronometer.setBase(SystemClock.elapsedRealtime());
                    mChronometer.setVisibility(View.VISIBLE);
                    mChronometer.start();
                    mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                        @Override
                        public void onChronometerTick(Chronometer chronometer) {
                            mRecordChronoTick++;
                            if(RecordTimeLimit != 0){
                                if(mRecordChronoTick==RecordTimeLimit){
                                    mChronometer.stop();
                                    mMediaRecorder.stop();
                                    mMediaRecorder.reset();
                                    mChronometer.setVisibility(View.INVISIBLE);
                                    mIsRecording=false;
                                    mRecordImageButton.setImageResource(R.mipmap.vidpiconline);
                                    startPreview();
                                }
                            }
                        }
                    });



                } else {
                    //Toast.makeText(this, "Permission to write is not granted", Toast.LENGTH_SHORT).show();
                    if (shouldShowRequestPermissionRationale(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        Toast.makeText(this, "app needs to be able to save videos", Toast.LENGTH_SHORT).show();


                    }
                    requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_STORAGE_PERMISSION_RESULT);
                }
            } else {
                mIsRecording = true;
                mRecordImageButton.setImageResource(R.mipmap.vidpicbusy);
                try {
                    createVideoFileName();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                startRecord();
                mMediaRecorder.start();
                mChronometer.setBase(SystemClock.elapsedRealtime());
                mChronometer.setVisibility(View.VISIBLE);
                mChronometer.start();

            }

        }
    }

    private Size mVideoSize;
    private MediaRecorder mMediaRecorder;

    private void setupMediaRecorder() throws IOException {
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.SURFACE);
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mMediaRecorder.setOutputFile(mVideoFileName);
        mMediaRecorder.setVideoEncodingBitRate(BitEncodingRate);
        mMediaRecorder.setVideoFrameRate(FrameRate);

         mMediaRecorder.setVideoSize(mVideoSize.getWidth(), mVideoSize.getHeight());
        mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mMediaRecorder.setOrientationHint(mTotalRotation);





        mMediaRecorder.prepare();
    }
    //

    //Capturing and Saving Videos
    private void startRecord() {
        try {
            if (mIsRecording) {
                setupMediaRecorder();
            } else if (mIsTimelapse) {
                setupTimelapse();
            }
            SurfaceTexture surfaceTexture = mTextureView.getSurfaceTexture();
            surfaceTexture.setDefaultBufferSize(mPreviewSize.getWidth(), mPreviewSize.getHeight());
            Surface previewSurface = new Surface(surfaceTexture);
            Surface recordSurface = mMediaRecorder.getSurface();
            mCaptureRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_RECORD);
            mCaptureRequestBuilder.addTarget(previewSurface);
            mCaptureRequestBuilder.addTarget(recordSurface);

            mCameraDevice.createCaptureSession(Arrays.asList(previewSurface, recordSurface, mImageReader.getSurface()),
                    new CameraCaptureSession.StateCallback() {


                        @Override
                        public void onConfigured(CameraCaptureSession session) {
                            try {
                                session.setRepeatingRequest(mCaptureRequestBuilder.build(),null,null);
                            } catch (CameraAccessException e) {
                                e.printStackTrace();
                            }
                            //mRecordCaptureSession = session;

                            /*try {
                                mRecordCaptureSession.setRepeatingRequest(mCaptureRequestBuilder.build(), null, null);
                            } catch (CameraAccessException e) {
                                e.printStackTrace();
                            }*/
                        }

                        @Override
                        public void onConfigureFailed(@NonNull CameraCaptureSession session) {

                        }
                    }, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Adding a Chronometer timer for recording
    private Chronometer mChronometer;
//now lets set up for still camera capture

    private SeekBar mSeekbar;
    private Size mImageSize;
    private Size mRawImageSize;




    private final ImageReader.OnImageAvailableListener mOnImageAvailableListener =
            new ImageReader.OnImageAvailableListener() {
                @Override
                public void onImageAvailable(ImageReader reader) {

                    //Toast.makeText(getApplicationContext(), "AHH", Toast.LENGTH_SHORT).show();
                    Image image = reader.acquireLatestImage();



                    if (!isAdjustingWB2) {
                        mCaptureRequestBuilder.set(CaptureRequest.CONTROL_EFFECT_MODE, mCameraEffect);
                        mCaptureRequestBuilder.set(CaptureRequest.JPEG_QUALITY,(byte)JPEGQuality);
                        mBackgroundHandler.post(new ImageSaver(image, mCaptureResult, mCameraCharacteristics));
                        //mBackgroundHandler.post(new ImageSaver(image, mCaptureResult, mCameraCharacteristics));


                    } else {


                        image.close();
                    }



                }
            };

    private final ImageReader.OnImageAvailableListener mOnRawImageAvailableListener =
            new ImageReader.OnImageAvailableListener() {
                @Override
                public void onImageAvailable(ImageReader reader) {
                    if (!mIsWritingRawImage && !mIsWritingImage) {
                        Image image = reader.acquireLatestImage();
                        Image.Plane[] planes = image.getPlanes();
                         Bytebufferplane1 = null;
                        if (planes.length > 0) {
                            Bytebufferplane1 = planes[0].getBuffer();
                        }
                        pixelValues = new int[BAYERHEIGHT][BAYERWIDTH];


                        float mSampleLocationX,mSampleLocationY;
                        if(AdjustWhiteBalanceonRawCapture){


                        if (Bytebufferplane1 != null) {
                            temp=0;
                            temp2=0;
                            counterr = 0;
                            if(BallInspectorx<0){
                                mSampleLocationX=0;
                            }else{
                                mSampleLocationX=BallInspectorx-(WhiteBalanceBallInspector.getWidth()/8);
                            }
                            if(BallInspectory<0){
                                mSampleLocationY=0;
                            } else{
                                mSampleLocationY=(float)(BallInspectory-WhiteBalanceBallInspector.getHeight()/8);
                            }
                            if(((double)mCurrentHeight/(double)mCurrentWidth)>(14.0/9.0)){
                                height =(int)((mTextureView.getWidth()-mSampleLocationX)*(image.getHeight()*0.75/mTextureView.getWidth()))+380;
                                width=(int)((mSampleLocationY*(image.getWidth()/mTextureView.getHeight())));
                            }else if((((double)mCurrentHeight/(double)mCurrentWidth)<(double)14.0/9.0) &&
                            (((double)mCurrentHeight/(double)mCurrentWidth))>(double)(1.2)){
                                height=(int)((mTextureView.getWidth()-mSampleLocationX)*(image.getWidth()/mTextureView.getWidth()));
                                width=(int)(mSampleLocationY*(image.getWidth()*1.33/mTextureView.getHeight()))-506;
                                if(width<0){
                                    width=0;
                                }else if(width>4044-128){
                                    width=4044-128;
                                }
                                if(height>3040){
                                    height=3042;
                                }
                            }else if(((double)mCurrentHeight/(double)mCurrentHeight)<(1.2)){
                                height =(int)(mSampleLocationY*(image.getHeight()/mTextureView.getWidth()))-380;
                                width=(int)(mSampleLocationX*(image.getWidth()/mTextureView.getHeight()));
                            }else{
                                height=(int)(mSampleLocationY*(image.getHeight()/mTextureView.getWidth()));
                                width=(int)(mSampleLocationX*(image.getWidth()/mTextureView.getHeight()));

                            }


                                int offsetWidth=width%2;
                                int lastindex=0;
                                Mat mat= new Mat();
                            //
                            rawWidth=image.getWidth();
                            rawHeight=image.getHeight();
                            totalResult=new int[rawHeight][rawWidth];
                            totalResult1D=new int [rawHeight*rawWidth];
                                imageWidth=image.getWidth();
                                imageHeight=image.getHeight();



                                mMat=new Mat(image.getHeight(),image.getWidth(),CV_16UC1);
                                count2=0;
                            byte[] rawByte=new byte[Bytebufferplane1.remaining()];
                            Bytebufferplane1.get(rawByte);




                                //int height = (int) (BallInspectory * (image.getHeight() / mTextureView.getWidth()));
                                //int width = (int) (BallInspectorx * (image.getWidth() / mTextureView.getHeight()));
                                
                                //Toast.makeText(Camera2VideoImageActivity.this, "H:"+height+"W:"+width, Toast.LENGTH_SHORT).show();
                                for(int j=0;j<rawHeight;j++){
                                    counterr=0;
                                    for (int i=0; i<rawWidth*2;i++){
                                        temp=rawByte[((i)+((imageWidth)*j*2))]&0xFF;
                                        if(i%2==1){
                                            totalResult[j][counterr]=(temp<<8)+temp2;
                                            //totalResult1D[count2]=totalResult[j][counterr];
                                            count2++;
                                            counterr++;
                                        }else{
                                            temp2=temp;
                                        }
                                    }
                                }
                            Toast.makeText(Camera2VideoImageActivity.this, "Finished", Toast.LENGTH_SHORT).show();
                            for (int j = height; j < height + BAYERHEIGHT; j++) {
                                counterr = 0;
                                for (int i = width*2; i < (width*2) + (BAYERWIDTH * 2); i++) {
                                    temp = Bytebufferplane1.get((i) + ((image.getWidth()) * j*2)) & 0xFF;
                                    if (i % 2 == 1) {
                                        pixelValues[j - height][counterr] = (temp << 8) + temp2;
                                        counterr++;
                                    } else {
                                        temp2 = temp;
                                    }
                                    lastindex=(i)+((image.getWidth())*j*2);
                                }
                            }
                            Toast.makeText(Camera2VideoImageActivity.this, "H:"+height+"W:"+width, Toast.LENGTH_SHORT).show();
                                //Testing MinJae's Code REEE
                                int mFilterArrangement = mCameraCharacteristics.get(mCameraCharacteristics.SENSOR_INFO_COLOR_FILTER_ARRANGEMENT);
                                if (mFilterArrangement == 0) {
                                    s = "RG\nGB\n\n";
                                    totalR = totalG = totalB = 0;
                                    for (int i = 0; i < BAYERHEIGHT; i++) {
                                        for (int j = 0; j < BAYERWIDTH; j++) {
                                            if (i % 2 == 0) {
                                                if (j % 2 == 0) {
                                                    totalR = totalR + pixelValues[i][j];
                                                    //RedPixelValues2[i][j]=pixelValues[i][j];

                                                    //adds up the total Red Values
                                                }
                                                if (j % 2 == 1) {
                                                    totalG = totalG + pixelValues[i][j];
                                                    //addes up the Total Green Values Pt 1
                                                }
                                            }
                                            if (i % 2 == 1) {
                                                if (j % 2 == 0) {
                                                    totalG = totalG + pixelValues[i][j];
                                                    //Adds up the Total GreeanValues Pt 2
                                                }
                                                if (j % 2 == 1) {
                                                    totalB = totalB + pixelValues[i][j];
                                                    //adds up all blue
                                                }
                                            }
                                        }

                                        s = s + "\n\n";
                                    }
                                } else if (mFilterArrangement == 1) {
                                    s = "GR\nBG\n\n";
                                    for (int i = 0; i < BAYERHEIGHT; i++) {
                                        for (int j = 0; j < BAYERWIDTH; j++) {
                                            if (i % 2 == 0) {
                                                if (j % 2 == 0) {
                                                    totalG = totalG + pixelValues[i][j];
                                                }
                                                if (j % 2 == 1) {
                                                    totalR = totalR + pixelValues[i][j];
                                                }
                                            }
                                            if (i % 2 == 1) {
                                                if (j % 2 == 0) {
                                                    totalB = totalB + pixelValues[i][j];
                                                }
                                                if (j % 2 == 1) {
                                                    totalG = totalG + pixelValues[i][j];
                                                }
                                            }
                                        }
                                        s = s + "\n\n";
                                    }
                                } else if (mFilterArrangement == 2) {
                                    s = "GB\nRG\n\n";
                                    for (int i = 0; i < BAYERHEIGHT; i++) {
                                        for (int j = 0; j < BAYERWIDTH; j++) {
                                            if (i % 2 == 0) {
                                                if (j % 2 == 0) {
                                                    totalG = totalG + pixelValues[i][j];
                                                }
                                                if (j % 2 == 1) {
                                                    totalB = totalB + pixelValues[i][j];
                                                }
                                            }
                                            if (i % 2 == 1) {
                                                if (j % 2 == 0) {
                                                    totalR = totalR + pixelValues[i][j];
                                                }
                                                if (j % 2 == 1) {
                                                    totalG = totalG + pixelValues[i][j];
                                                }
                                            }
                                        }
                                        s = s + "\n\n";
                                    }
                                } else if (mFilterArrangement == 3) {
                                    s = "BG\nGR\n\n";
                                    for(int i=0;i<BAYERHEIGHT;i=i+2) {
                                        for (int j = 0; j < BAYERWIDTH; j=j+2) {
                                            /*if (i % 2 == 0) {
                                                if (j % 2 == 0) {
                                                    totalB = totalB + pixelValues[i][j];
                                                }
                                                if (j % 2 == 1) {
                                                    totalG = totalG + pixelValues[i][j];
                                                }
                                            }
                                            if (i % 2 == 1) {
                                                if (j % 2 == 0) {
                                                    totalG = totalG + pixelValues[i][j];
                                                }
                                                if (j % 2 == 1) {
                                                    totalR = totalR + pixelValues[i][j];
                                                }
                                            }
                                            s = s + pixelValues[i][j] + " ";
                                        }
                                        s = s + "\n\n";*/

                                            totalB = totalB + pixelValues[i][j];
                                            totalG = totalG + pixelValues[i][j + 1];
                                            totalG = totalG + pixelValues[i + 1][j];
                                            totalR = totalR + pixelValues[i + 1][j + 1];
                                        }

                                    }
                                }

                                float normal =(float) Math.sqrt(Math.pow(totalR,2)+Math.pow(totalG,2)+(Math.pow(totalB,2)));
                                totalR = (float)((totalR/32)/normal);
                                totalG = (float)((totalG/64)/normal);
                                totalB = (float)((totalB)/32)/normal;
                                for(int i=lastindex-128;i<lastindex;i++){
                                    Bytebufferplane1.put(i,(byte)0);
                                }
                                for(int i=lastindex-128;i<lastindex;i++){
                                    Bytebufferplane1.put(i + image.getWidth() * 2, (byte) 0);
                                }
                                for(int i = lastindex - 128; i < lastindex; i++){
                                    Bytebufferplane1.put(i + image.getWidth() * 4, (byte) 0);
                                }for(int i = lastindex - 128; i < lastindex; i++)
                                    { Bytebufferplane1.put(i + image.getWidth() * 6, (byte) 0);}

                                for(int i= lastindex - 128; i < lastindex; i++){
                                    Bytebufferplane1.put(i + image.getWidth() * 8, (byte) 0);
                                }
                                for(int i= lastindex - 128; i < lastindex; i++){
                                    Bytebufferplane1.put(i+image.getWidth()*10,(byte)0);
                                }
                        }



                                Toast.makeText(getApplicationContext(), "R: " + totalR + ", G: " + totalG + ", B: " + totalB, Toast.LENGTH_LONG).show();
                                    if (!isAdjustingWB2) {

                                        mBackgroundHandler.post(new ImageSaver(image, mCaptureResult, mCameraCharacteristics));


                                    } else if(isAdjustingWB2 && AdjustWhiteBalanceonRawCapture ) {
                                        mWBMode=-1;
                                        ColorSpaceInputBoolean=true;
                                        mVectorR=(float)(totalG/totalR);
                                        mVectorG_EVEN=1;
                                        mVectorG_ODD=1;
                                        mVectorB=(float)(totalG/totalB);
                                        connectCamera();
                                        startPreview();

                                        image.close();
                                    }
                            if(ConvertRAWtoPNG && AdjustWhiteBalanceonRawCapture){
                                CaptureandConvertRAWtoPNG();



                                //loadingalphabool=false;


                            }



                        }

                    }
                }
            };



    private void AutoWhiteBalanceLock(){
        mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AWB_LOCK,true);
        Toast.makeText(getApplicationContext(), "AWB Locked", Toast.LENGTH_SHORT).show();
        ///needs work

    }
    private void AutoWhiteBalanceUnlock(){
        mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AWB_LOCK,false);
        Toast.makeText(getApplicationContext(), "AWB Unlocked", Toast.LENGTH_SHORT).show();
        ///needs work

    }
    private void AutoExposureLock(){
        mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AE_LOCK, true);

        Toast.makeText(getApplicationContext(), "AE locked", Toast.LENGTH_SHORT).show();

        ///needs work

    }
    private void AutoExposureUnlock(){
        mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AE_LOCK, false);
        Toast.makeText(getApplicationContext(), "AE Unlocked", Toast.LENGTH_SHORT).show();
        ///needs work

    }



    private static final int STATE_PREVIEW = 0;
    private static final int STATE_WAIT_LOCK = 1;
    private int mCaptureState = STATE_PREVIEW;


    private void lockFocus() {
        {
            Toast.makeText(getApplicationContext(), "Focus Locked", Toast.LENGTH_SHORT).show();
            //startStillCaptureRequest();
            mCaptureState = STATE_WAIT_LOCK;
            if(!manualFocusEnableIsChecked){
                mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, CaptureRequest.CONTROL_AF_TRIGGER_START);
            }
            count2=0;
            try {
                if (mIsRecording) {
                    mRecordCaptureSession.capture(mCaptureRequestBuilder.build(), mRecordCaptureCallback, mBackgroundHandler);

                } else {
                    mPreviewCaptureSession.capture(mCaptureRequestBuilder.build(), mPreviewCaptureCallback, mBackgroundHandler);
                }
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
            //startStillCaptureRequest();


        }

        //startStillCaptureRequest();
    }

    /*private void unLockFocus() {
        if (AutoLocks == 1) {
            try {
                //mCaptureState=STATE_WAIT_LOCK;
                mCaptureState = STATE_PREVIEW;
                mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER,
                        CameraMetadata.CONTROL_AF_TRIGGER_CANCEL);
                mPreviewCaptureSession.capture(mCaptureRequestBuilder.build(), mPreviewCaptureCallback,
                        mBackgroundHandler);
                //
                mPreviewCaptureSession.capture(mCaptureRequestBuilder.build(),mPreviewCaptureCallback,
                        mBackgroundHandler);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        AutoLocks=0;
    }*/

    private ImageButton mStillImageButton;
    //image capture
    //part 17 capturing a still image in a preview mode
    private File mImageFolder;
    private String mImageFileName;


    private void createImageFolder() {
        File imageRawFile=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File imageFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        mImageFolder = new File(imageFile, "Camera2_Video_Image");
        mRawGalleryFolder = new File(imageRawFile,"Camera2_Video_Image_RAW");
        //check to see if the folder is already created
        if (!mImageFolder.exists()) {
            mImageFolder.mkdirs();

        }
        if (!mRawGalleryFolder.exists()) {
            mRawGalleryFolder.mkdirs();
        }

    }

//now we have to call the videoFolder onCreate

    private File createImageFileName() throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        //there are two types of SimpleDateFormat and Date()
        String prepend = "JPEG_" + timestamp + "_";
        File imageFile = File.createTempFile(prepend, ".jpg", mImageFolder);
        mImageFileName = imageFile.getAbsolutePath();
        return imageFile;

    }

    private File createRawImageFileName() throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        //there are two types of SimpleDateFormat and Date()
        String prepend = "RAW_" + timestamp + "_";
        File imageRawFile = File.createTempFile(prepend, ".dng", mRawGalleryFolder);
        mRawFileName = imageRawFile.getAbsolutePath();
        return imageRawFile;

    }

    private void startStillCaptureRequest() {
        try {


            if (mIsRecording || mIsTimelapse) {
                mCaptureRequestBuilder = mCameraDevice.createCaptureRequest(
                        CameraDevice.TEMPLATE_VIDEO_SNAPSHOT);

            } else if(!mIsRecording ||!mIsTimelapse) {
                mCaptureRequestBuilder = mCameraDevice.createCaptureRequest(
                        CameraDevice.TEMPLATE_STILL_CAPTURE);
                mCaptureRequestBuilder.set(CaptureRequest.JPEG_QUALITY,(byte)JPEGQuality);
            }


            //mCaptureRequestBuilder.addTarget(mImageReader.getSurface());
            if(mRawImageCaptureon){
                mCaptureRequestBuilder.addTarget(mImageReader.getSurface());
                mCaptureRequestBuilder.addTarget(mRawImageReader.getSurface());


            }if(CapturePngBoolean){
                mCaptureRequestBuilder.addTarget(mImageReader.getSurface());
                //Bitmap bm=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
                //bm.copyPixelsFromBuffer(ByteBuffer);


                String timestamp=new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String prepend= "PNG"+timestamp;
                File PngFile=null;
                try {

                     PngFile=File.createTempFile(prepend,".png",mImageFolder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String mPngImageFileName=PngFile.getAbsolutePath();
                Toast.makeText(this, "PNG Captured", Toast.LENGTH_SHORT).show();
                // bitmap=mTextureView.getBitmap();
                //Canvas canvas=new Canvas(bitmap);
                try {
                    output=new FileOutputStream(mPngImageFileName);
                    //bitmap.compress(Bitmap.CompressFormat.PNG,100,output);
                    output.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                //capturePng Urgh
                





            } if(JPEGCaptureOn) {
                mCaptureRequestBuilder.addTarget(mImageReader.getSurface());
            }
            mCaptureRequestBuilder.set(CaptureRequest.JPEG_ORIENTATION,(mTotalRotation+180)%360);





            // mCaptureRequestBuilder.set(CaptureRequest.JPEG_ORIENTATION, mTotalRotation);
            if(mFlashMode==2){
                mCaptureRequestBuilder.set(CaptureRequest.FLASH_MODE, FLASH_MODE_SINGLE);

            }
            //setSettings();

            //Testing Exposure Time
            //units nanoseconds

            CameraCaptureSession.CaptureCallback stillCaptureCallback = new
                    CameraCaptureSession.CaptureCallback() {
                        @Override
                        public void onCaptureStarted(CameraCaptureSession session, CaptureRequest request, long timestamp, long frameNumber) {
                            super.onCaptureStarted(session, request, timestamp, frameNumber);


                            try {

                                createImageFileName(); //forImage
                                if (mRawImageCaptureon) {
                                    createRawImageFileName(); //for RawImage
                                }
                                if(ConvertRAWtoPNG){
                                    createPNgImageFileName();
                                }




                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        @Override
                        public void onCaptureCompleted (CameraCaptureSession session,CaptureRequest request, TotalCaptureResult result)
                        {
                            super.onCaptureCompleted(session,request,result);
                            mCaptureResult=result;

                            if(count2< -1){
                                new CountDownTimer((long)(intervalTime*1000), 100){

                                    @Override
                                    public void onTick(long millisUntilFinished) {

                                    }

                                    @Override
                                    public void onFinish() {
                                        startStillCaptureRequest();
                                        if(mFlashMode==2){
                                            mCaptureRequestBuilder.set(CaptureRequest.FLASH_MODE,CameraMetadata.FLASH_MODE_TORCH);
                                        }
                                        mIsWritingImage=false;

                                    }
                                }.start();
                            }
                            counterr++;

                            Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
                            Bytebufferplane1=null;

                        }


                    };

            if (mIsRecording || mIsTimelapse) {
                mRecordCaptureSession.capture(mCaptureRequestBuilder.build(), stillCaptureCallback, null);

            } else {

                mPreviewCaptureSession.capture(mCaptureRequestBuilder.build(), stillCaptureCallback, null);


            }

        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

    }

    private void createPNgImageFileName() {

    }

    /*private void setSettings() {
        if(BooleanOpticalStabilizationOn){
            mCaptureRequestBuilder.set(CaptureRequest.LENS_OPTICAL_STABILIZATION_MODE, CaptureRequest.LENS_OPTICAL_STABILIZATION_MODE_ON);

        } else {
            mCaptureRequestBuilder.set(CaptureRequest.LENS_OPTICAL_STABILIZATION_MODE, CaptureRequest.LENS_OPTICAL_STABILIZATION_MODE_OFF);

        }
        if(mSceneMode != CONTROL_SCENE_MODE_DISABLED){
            mCaptureRequestBuilder.set(CaptureRequest.CONTROL_MODE, CaptureRequest.CONTROL_MODE_USE_SCENE_MODE);
            mCaptureRequestBuilder.set(CaptureRequest.CONTROL_SCENE_MODE, mSceneMode);
        }else{
            mCaptureRequestBuilder.set(CaptureRequest.CONTROL_MODE, CaptureRequest.CONTROL_MODE_AUTO);
        }


    }*/

    private class ImageSaver implements Runnable {

        private final Image mImage;
        private final CaptureResult mCaptureResult;
        private final CameraCharacteristics mCameraCharacteristics;


        private ImageSaver(Image image, CaptureResult captureResult, CameraCharacteristics mCameraCharacteristics) {
            mImage = image;
            mCaptureResult = captureResult;
            this.mCameraCharacteristics = mCameraCharacteristics;

        }



        @Override
        public void run() {

            int format = mImage.getFormat();
            switch(format) {
                case ImageFormat.JPEG:




                    ByteBuffer byteBuffer = mImage.getPlanes()[0].getBuffer();
                     bytes = new byte[byteBuffer.remaining()];
                        byteBuffer.get(bytes);

                    FileOutputStream fileOutputStream = null;
                    try {
                        fileOutputStream = new FileOutputStream(mImageFileName);
                        Toast.makeText(Camera2VideoImageActivity.this, "JPEG saved", Toast.LENGTH_SHORT).show();
                        try {
                            if(CapturePngBoolean){
                                output.write(bytes);
                            }

                            fileOutputStream.write(bytes);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } finally {
                        mImage.close();
                        // media store update - images
                        /*Intent mediaStoreUpdateIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                        mediaStoreUpdateIntent.setData(Uri.fromFile(new File(mImageFileName)));
                        sendBroadcast(mediaStoreUpdateIntent);*/
                        if(fileOutputStream != null){
                            try {
                                fileOutputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        mIsWritingImage = false;
                    }
                    //mIsWritingImage=true;
                    break;
                case ImageFormat.RAW_SENSOR:
                    //case ImageFormat.RAW10:
                    //case ImageFormat.RAW12:
                    //case ImageFormat.RAW_PRIVATE:
                    // 1
                    /*try {
                        createRawImageFileName();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/
                    DngCreator dngCreator = new DngCreator(mCameraCharacteristics, mCaptureResult);
                    FileOutputStream rawFileOutputStream = null;
                    try {
                        rawFileOutputStream = new FileOutputStream(mRawFileName);
                        Toast.makeText(Camera2VideoImageActivity.this, "R: " + totalR + ", G: " + totalG + ", B: " + totalB, Toast.LENGTH_LONG).show();

                        dngCreator.writeImage(rawFileOutputStream, mImage);

                        Toast.makeText(getApplicationContext(), "RAW saved", Toast.LENGTH_SHORT).show();

                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Error in saving RAW", Toast.LENGTH_SHORT).show();
                    } finally {
                        mImage.close();
                        // media store update - images
                        /*Intent mediaStoreUpdateIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                        mediaStoreUpdateIntent.setData(Uri.fromFile(new File(mRawFileName)));
                        sendBroadcast(mediaStoreUpdateIntent);*/
                        if(rawFileOutputStream != null){
                            try {
                                rawFileOutputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        //mIsWritingRawImage = false;
                    }
                    //mIsWritingRawImage = true;
                    break;
                case ImageFormat.FLEX_RGB_888:
                    FileOutputStream pngFileOutStream=null;
                    Bitmap pngBitmap=null;
                    if(mImage !=null){
                        Image.Plane[] plane123=mImage.getPlanes();
                        ByteBuffer buffer=plane123[0].getBuffer();
                        int pixelStride=plane123[0].getPixelStride();
                        int rowStride=plane123[0].getRowStride();
                        int rowPadding=rowStride-pixelStride*mImage.getWidth();
                        pngBitmap=Bitmap.createBitmap(100,100, Bitmap.Config.ARGB_8888);
                        pngBitmap.copyPixelsFromBuffer(buffer);
                        try {
                            OutputStream imageStream=new FileOutputStream("PNGIMAGETEST.png");
                            pngBitmap.compress(Bitmap.CompressFormat.PNG,100,imageStream);
                            imageStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    break;

            }

        }
    }


    //Part 18. Capturing a photo while recording
    private CameraCaptureSession mRecordCaptureSession;
    private CameraCaptureSession.CaptureCallback mRecordCaptureCallback = new
            CameraCaptureSession.CaptureCallback() {

                private void process(CaptureResult captureResult) {
                    switch (mCaptureState) {
                        case STATE_PREVIEW:
                            //Do nothing
                            break;

                        case STATE_WAIT_LOCK:


                            mCaptureState = STATE_PREVIEW;
                            Integer afState = captureResult.get(CaptureResult.CONTROL_AF_STATE);
                            if (afState == CaptureResult.CONTROL_AF_STATE_FOCUSED_LOCKED || afState == CaptureResult.CONTROL_AF_STATE_NOT_FOCUSED_LOCKED) {
                                Toast.makeText(getApplicationContext(), "Autofocus locked", Toast.LENGTH_SHORT).show();

                                //startStillCaptureRequest();

                            }

                            break;
                    }

                }

                @Override
                public void onCaptureCompleted(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull TotalCaptureResult result) {
                    super.onCaptureCompleted(session, request, result);
                    process(result);
                    mCaptureResult = result;




                }
            };

    //Recording Audio pt 19


    //Part 20 time-lapse video
    //long press on record button
    private boolean mIsTimelapse = false;

    private void setupTimelapse() throws IOException {
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.SURFACE);
        mMediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_TIME_LAPSE_HIGH));
        mMediaRecorder.setOutputFile(mVideoFileName);
        //Frequency of how fast
        mMediaRecorder.setCaptureRate(VideoTimelapsSecondStep);
        mMediaRecorder.setOrientationHint(mTotalRotation);
        mMediaRecorder.prepare();
    }

    //Updating MediaStore Database done
//Raw image Capture Part 1
    private static Boolean contains(int[] modes, int mode) {
        if (modes == null) {
            return false;

        }
        for (int i : modes) {
            if (i == mode) {
                return true;
            }
        }
        return false;
    }

    //Create an Activity member for the raw folder
    private File mRawGalleryFolder;
    private String mRawFileName;

    //Create a file for the captured raw image
    private static File mRawImageFile;
    private static File mImageFile;


    CameraCharacteristics cameraCharacteristics;
    //RAW Image Capture part2
    private CaptureResult mCaptureResult;

    private boolean mIsWritingImage = false;
    private boolean mIsWritingRawImage = false;
    //Now Were going to create a pop-up menu
    //ISO CHANGE
//ASpect Ratio stuff
    private static final String TAG = Camera2VideoImageActivity.TAG;

    private void adjustAspectRatio(int videoWidth, int videoHeight) {
        int viewWidth = mTextureView.getWidth();
        int viewHeight = mTextureView.getHeight();
        double aspectRatio = (double) videoHeight / videoWidth;

        int newWidth, newHeight;
        if (viewHeight > (int) (viewWidth * aspectRatio)) {
            // limited by narrow width; restrict height
            newWidth = viewWidth;
            newHeight = (int) (viewWidth * aspectRatio);
        } else {
            // limited by short height; restrict width
            newWidth = (int) (viewHeight / aspectRatio);
            newHeight = viewHeight;
        }
        int xoff = (viewWidth - newWidth) / 2;
        int yoff = (viewHeight - newHeight) / 2;
        Log.v(TAG, "video=" + videoWidth + "x" + videoHeight +
                " view=" + viewWidth + "x" + viewHeight +
                " newView=" + newWidth + "x" + newHeight +
                " off=" + xoff + "," + yoff);

        android.graphics.Matrix txform = new android.graphics.Matrix();
        mTextureView.getTransform(txform);
        RectF textureRectF=new RectF(0,0,viewWidth,viewHeight);
        RectF previewRectF=new RectF(0,0, newWidth,newHeight);
        txform.setScale((float) newWidth / viewWidth, (float) newHeight / viewHeight);
        int rotation=getWindowManager().getDefaultDisplay().getRotation();
        if(rotation==Surface.ROTATION_0||rotation==Surface.ROTATION_180){
            txform.postTranslate(xoff, yoff);
            mTextureView.setTransform(txform);
        }else if(rotation==Surface.ROTATION_90||rotation==Surface.ROTATION_270){
            txform.postRotate(0);
        }


    }
    private void checkJPEGWriteStoragePermission() throws IOException{
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){


            }else{
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_STORAGE_PERMISSION_RESULT);


            }
        }
    }

    public void RefreshScreen() {
        RefreshBoolean=true;
        // SharedPreferences sharedprefs1 = null;
        sharedprefs1 = PreferenceManager.getDefaultSharedPreferences(Camera2VideoImageActivity.this);
        if(SettingresolutionChanged){
            final String resolutionlist=sharedprefs1.getString("resolution_list", null);

            if(resolutionlist!=null){
                Size1=previewSizes[Integer.parseInt(resolutionlist)];
                mCurrentWidth=Size1.getWidth();
                mCurrentHeight=Size1.getHeight();
                adjustAspectRatio(Size1.getHeight(), Size1.getWidth());
                setupCamera(Size1.getHeight(), Size1.getWidth());
            }



            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
                connectCamera();
            }

        }

        String BitEncodingRateString=sharedprefs1.getString("EncodingBitRate","8000000");
        readRawonTap=sharedprefs1.getBoolean("readRawonTap",false);
        BitEncodingRate=Integer.parseInt(BitEncodingRateString);
        String FrameRateString=sharedprefs1.getString("ChangeVideoFPS","30");
        FrameRate=Integer.parseInt(FrameRateString);
        boolean ExportTxtFileboolean=sharedprefs1.getBoolean("exporttxtfile",false);
        CapturePngBoolean=sharedprefs1.getBoolean("Capture_PNG",false);
        ShowCIEXYZValuesBoolean=sharedprefs1.getBoolean("ShowCIEXYZValues",false);
        ExposureCompensationSeekBarboolean=sharedprefs1.getBoolean("ExposureCompensationSwitch",false);
        JPEGCaptureOn=sharedprefs1.getBoolean("Capture_JPEG",true);
        PipelineEditorNumber= Integer.parseInt(sharedprefs1.getString("pipelineEditor","0"));
        final String TempSecondIntervalString=sharedprefs1.getString("PictureSecondStep","5");
        ShowRealTimeInfoboolean=sharedprefs1.getBoolean("show_real_time_info",false);
        ExportasRGBasTextboolean=sharedprefs1.getBoolean("exportRGBasText",false);
        ExportAsRGGBasTextboolean=sharedprefs1.getBoolean("exportRGGBasText",false);
        NoiseReductionModesint=Integer.parseInt(sharedprefs1.getString("noise_reduction_mode","1"));
        AntiBandingModeint = Integer.parseInt(sharedprefs1.getString("control_antibanding_mode","3"));
        //PatternTestint=Integer.parseInt(sharedprefs1.getString("sensor_available_test_modes","0"));
        EdgeMode=Integer.parseInt(sharedprefs1.getString("edge_options","1"));
        String TempRecordTimeLimitString=sharedprefs1.getString("RecordTimeStop","0");
        RecordTimeLimit=Integer.parseInt(TempRecordTimeLimitString);
        //Colorfilter=Integer.parseInt(sharedprefs1.getString("bayer_filter_change",""+mCameraCharacteristics.get(mCameraCharacteristics.SENSOR_INFO_COLOR_FILTER_ARRANGEMENT)));
        HotPixelMode=Integer.parseInt(sharedprefs1.getString("hot_pixel_mode","0"));
        JPEGQuality=Byte.parseByte(sharedprefs1.getString("set_jpeg_quality","100"));
        ToneMapMode=Integer.parseInt(sharedprefs1.getString("tonemap_mode","1"));
        ConvertRAWtoPNG=sharedprefs1.getBoolean("ConvertRAWtoPNG",false);
        Capture_JPEG=sharedprefs1.getBoolean("Capture_JPEG",true);
        trackfacesbool=sharedprefs1.getBoolean("trackfaces",false);





        //startPreview();
        if(PipelineEditorNumber==0){
            //Toast.makeText(this, "Do Nothing", Toast.LENGTH_SHORT).show();
        }
        if(PipelineEditorNumber==1){
            Toast.makeText(this, "Execute inverse Sensor Color Transform", Toast.LENGTH_SHORT).show();
        }
        if(PipelineEditorNumber==2){
            Toast.makeText(this, "Execute inverse Forward Matrix", Toast.LENGTH_SHORT).show();
        }
        if(PipelineEditorNumber==3){
            Toast.makeText(this, "Execute Inverse Calibration Transform", Toast.LENGTH_SHORT).show();
        }
        if(ExposureCompensationSeekBarboolean) {
            ExposureCompensationSeekBar.setVisibility(View.VISIBLE);
            ExposureCompensationtextview.setVisibility(View.VISIBLE);
        }else{
            ExposureCompensationSeekBar.setVisibility(View.INVISIBLE);
            ExposureCompensationtextview.setVisibility(View.INVISIBLE);
        }
        
        if(ExportTxtFileboolean==true){
            //execute File Export

            if(!txtfolder.exists()){
                txtfolder.mkdirs();
            }
            String txttimestamp=new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String prepend="TXT_"+txttimestamp+"_";
            File txtfile= null;
            try {
                txtfile = File.createTempFile(prepend,".txt",txtfolder);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String mtxtFileName=txtfile.getAbsolutePath();
            try {
                FileOutputStream fileoutputstream=new FileOutputStream(mtxtFileName);
                String examplestring="RGGB_CHANNEL_VECTOR: "+rggbChannelVector.toString()+"\n"+"SENSOR_REFERENCE_ILLUMINANT1: " +mCameraCharacteristics.get(mCameraCharacteristics.SENSOR_REFERENCE_ILLUMINANT1)+ "SENSOR_REFERNCE_ILLUMINANT_2: "+mCameraCharacteristics.get(mCameraCharacteristics.SENSOR_REFERENCE_ILLUMINANT2)  + "\n"+ "SENSOR_COLOR_TRANSFORM1: "  +mCameraCharacteristics.get(mCameraCharacteristics.SENSOR_COLOR_TRANSFORM1)+ "\n"+"SENSOR_COLOR_TRANSFORM2: "+ mCameraCharacteristics.get(mCameraCharacteristics.SENSOR_COLOR_TRANSFORM2)+ "\n"+
                         "SENSOR_FORWARD_MATRIX1: "+mCameraCharacteristics.get(mCameraCharacteristics.SENSOR_FORWARD_MATRIX1)+"\n"+ "SENSOR_FORWARD_MATRIX2: "+mCameraCharacteristics.get(mCameraCharacteristics.SENSOR_FORWARD_MATRIX2) + "\n"
                        +"SENSOR_CALIBRATION_TRANSFORM1: "+ mCameraCharacteristics.get(mCameraCharacteristics.SENSOR_CALIBRATION_TRANSFORM1)+"\n"
                        +"SENSOR_CALIBRATION_TRANSFORM2: "+ mCameraCharacteristics.get(mCameraCharacteristics.SENSOR_CALIBRATION_TRANSFORM2)+"\n"


                        ;
                byte[] txtbytes= examplestring.getBytes();
                fileoutputstream.write(txtbytes);

            } catch (IOException e) {
                e.printStackTrace();
            }


            Toast.makeText(getApplicationContext(), "Text File Written(Code not complete)", Toast.LENGTH_SHORT).show();
            //write data



            SharedPreferences.Editor editor=sharedprefs1.edit();
            editor.putBoolean("exporttxtfile",false);
            editor.commit();


        }
        boolean ExportC2CCFileboolean=sharedprefs1.getBoolean("exportc2ccFile",false);
        if(ExportC2CCFileboolean==true){
            File file=getFileStreamPath("Testfile.C2CC");
            if(!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                FileOutputStream writer=openFileOutput(file.getName(),Context.MODE_PRIVATE);
                String string="Hello World!";
                writer.write(string.getBytes());
                //writer.flush();
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(getApplicationContext(), "C2CC File Writter(Code not complete)", Toast.LENGTH_SHORT).show();


            //execute File Export
            SharedPreferences.Editor editor2=sharedprefs1.edit();
            editor2.putBoolean("exportc2ccFile",false);
            editor2.commit();
        }






        boolean RawwithJPEg = sharedprefs1.getBoolean("Capture_Raw_With_JPEG", false);
        boolean OpticalStabilization = sharedprefs1.getBoolean("optical_stabilization", true);
        mRawImageCaptureon=RawwithJPEg;
        BooleanOpticalStabilizationOn=OpticalStabilization;
        int TempSecondInterval=Integer.parseInt(TempSecondIntervalString);
        SecondStep = TempSecondInterval;
        String TempTimeLimitString=sharedprefs1.getString("PictureTimeLimit","0");
        int TempTimeLimit=Integer.parseInt(TempTimeLimitString);
        String TempVideoSecondIntervalString =sharedprefs1.getString("VideoSecondStep","5");
        int TempVideoSecondInterval=Integer.parseInt(TempVideoSecondIntervalString);
        VideoTimelapsSecondStep = TempVideoSecondInterval;


        String TempVideoTimeLimitString=sharedprefs1.getString("VideoTimelapseTimeLimit","0");



        TempVideoTimeLimit=Integer.parseInt(TempVideoTimeLimitString);
        if (TempVideoTimeLimit==0){
            mVideoTimeLimitNumber=1;
        }else{
            mVideoTimeLimitNumber=0;
        }
        if (TempTimeLimit==0) {
            mPhotoTimeLimitNumber = 1;
        } else {
            mPhotoTimeLimitNumber = 0;
            PhotoBurstTimeStop = TempTimeLimit;
        }
        /*Toast.makeText(Camera2VideoImageActivity.this, "Name: " + mSetting
                + "Second Step: "+ TempSecondIntervalString + TempSecondInterval
                + "Capture Raw With JPEG: " + RawwithJPEg + "Optical Stabilization: " + OpticalStabilization+"scanned file string:  " + scannedfilestring+ "resolution number:" +resolutionlist , Toast.LENGTH_LONG).show();*/
        //ModifyXMLFile2();
        if(ShowRealTimeInfoboolean){
            mInfoTextView.setVisibility(View.VISIBLE);
        }else{
            mInfoTextView.setVisibility(View.INVISIBLE);
        }


    }

    //testingrgb to lab




    private BaseLoaderCallback mLoaderCallback=new BaseLoaderCallback(this){

        @Override
        public void onManagerConnected(int status) {
            switch (status){
                case LoaderCallbackInterface.SUCCESS:
                {
                    Log.i(TAG,"OPEN CV loaded successfully");
                }break;
                default:
                {
                    super.onManagerConnected(status);
                }break;
            }


        }
    };
    private void CaptureandConvertRAWtoPNG() {

        //this method converts a White Balanced image from Raw to PNG
        String date=new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());


        tempMat=new Mat(imageHeight,imageWidth,CV_16UC1);
        s1RawImage=new Mat(imageHeight,imageWidth,CV_16UC1);
        s2BlackLightSubration=new Mat(imageHeight,imageWidth,CV_16UC1);
        s3LeensCorrection=new Mat(imageHeight,imageWidth,CV_16UC1);
        s4NoiseReduction=new Mat(imageHeight,imageWidth,CV_16UC1);
        s5WhiteBalancing=new Mat(imageHeight,imageWidth,CV_16UC1);
        s6ColorSpace=new Mat(imageHeight,imageWidth,CV_16UC1);


        int blacklevel=mCameraCharacteristics.get(CameraCharacteristics.SENSOR_BLACK_LEVEL_PATTERN).getOffsetForIndex(0,0);
        int whitelevel=mCameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_WHITE_LEVEL);
        double constant;
        if(blacklightSubtractionIsEnabled){
            constant=(whitelevel/(whitelevel-blacklevel));
        }else{
            constant=1.00;
            blacklevel=0;
        }
        for(int j=0;j<totalResult.length;j++){
            for(int i=0; i<totalResult[0].length;i++){
                mMat.put(j,i,(totalResult[j][i]-blacklevel)*constant);
                if(j%2==0 && i%2==0){
                    s5WhiteBalancing.put(j,i,(totalResult[j][i]*mVectorB-blacklevel)*constant);
                }else if(j%2==1 && i%2==1){
                    s5WhiteBalancing.put(j,i,(totalResult[j][i]*mVectorR-blacklevel)*constant);
                }else{
                    s5WhiteBalancing.put(j,i,(totalResult[j][i]-blacklevel)*constant);
                }

            }
        }
        //demoisacing is finished


        //finalMat=new Mat(imageHeight,imageWidth,CV_16UC1);
        //Toast.makeText(Camera2VideoImageActivity.this, "Array2Mat Done", Toast.LENGTH_SHORT).show();
        //cvtColor(mMat,mMat2, Imgproc.COLOR_BayerBG2RGB);
        //mMat2.convertTo(mMat3, CV_16UC1,255);
       // mMat2.convertTo(mMat2, CV_16UC1,255);
        cvtColor(mMat,s1RawImage,Imgproc.COLOR_BayerBG2RGB);
        cvtColor(s5WhiteBalancing,s5WhiteBalancing,Imgproc.COLOR_BayerBG2RGB);
        //s1RawImage.convertTo(mMat3,CV_16UC1,255);
        s1RawImage.convertTo(s1RawImage,CV_16UC1,35536/whitelevel);
        s5WhiteBalancing.convertTo(s5WhiteBalancing,CV_16UC1,35536/whitelevel);



        Toast.makeText(this, "Demosiacing Done", Toast.LENGTH_SHORT).show();




        //loadingalphabool=false;
        MatOfInt matInt=new MatOfInt();
        matInt.fromArray(Imgcodecs.CV_IMWRITE_PNG_COMPRESSION,0);
        //File path=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"Camera2PNG_(fromRaw)");
        if(!PNGRAWfolder.exists()){
            PNGRAWfolder.mkdirs();
        }

        //s1 Raw Image is assigned colors and export
        String filename="temp"+date+".png";
        File file=new File(PNGRAWfolder,filename);
        Boolean bool=null;
        filename=file.toString();
        bool=Imgcodecs.imwrite(filename,s1RawImage,matInt);
        sixtyFours = new Mat(imageHeight, imageWidth, CV_16UC1);
        sixtyFours.setTo(new Scalar(16));
        cvtColor(sixtyFours, sixtyFours, Imgproc.COLOR_BayerBG2RGB);
        sixtyFours.convertTo(sixtyFours, CV_16UC1, 255);
        //sixtyFours.convertTo(sixtyFours, CV_16UC1, 255);

        Mat satMinusBlack = new Mat(totalResult.length, totalResult[0].length, CV_16UC1);

        Core.addWeighted(s1RawImage,1.0,sixtyFours,0.0,0.0,s2BlackLightSubration);

        MatOfInt matInt2 = new MatOfInt();
        matInt2.fromArray(Imgcodecs.CV_IMWRITE_PNG_COMPRESSION, 0);
        String filename2 = "s2BlackLightSubtraction"+date+".png";
        File file2 = new File(PNGRAWfolder, filename2);
        Boolean bool2 = null;
        filename2 = file2.toString();
        bool2 = Imgcodecs.imwrite(filename2, s2BlackLightSubration, matInt2);

        MatOfInt matInt3 = new MatOfInt();
        matInt3.fromArray(Imgcodecs.CV_IMWRITE_PNG_COMPRESSION, 0);
        //File path3 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String filename3 = "s5WhiteBalancing"+date+".png";
        File file3 = new File(PNGRAWfolder, filename3);
        Boolean bool3 = null;
        filename3 = file3.toString();
        bool3 = Imgcodecs.imwrite(filename3, s5WhiteBalancing, matInt3);


        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                //note: visibility has to be set on the same thread
                loadingtext.setVisibility(View.INVISIBLE);
                loadingemblem1.clearAnimation();
                loadingAnimation.cancel();
                loadingAnimation.reset();
                loadingemblem1.setVisibility(View.INVISIBLE);

                alphaview.setAlpha(0f);
            }
        });
        //Intent intent1=new Intent(this, Camera2VideoImageActivity.class);
        //startActivity(intent1);

    }
















}