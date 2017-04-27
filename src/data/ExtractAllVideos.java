package data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.opencv.core.Mat;

import har.Constants;

public class ExtractAllVideos {

	public ExtractAllVideos() {
		// TODO Auto-generated constructor stub
	}
	
	public void exe(ImageGUI VideoShow) throws IOException{
		File f = MyTools.mkdir(Classifiers.data_hog_Address,Classifiers.data_hog_name_tem);//保存路径
		File f2 = MyTools.mkdir(Classifiers.data_hog_Address,Classifiers.data_hog_label_tem);//保存路径
		
		FileWriter fw=new FileWriter(f);
		PrintWriter outAll=new PrintWriter(new BufferedWriter(fw));
		
		FileWriter fw2=new FileWriter(f2);
		PrintWriter outAll_label=new PrintWriter(new BufferedWriter(fw2));
		
		MyTools.showTips("\n特征提取中...",1);
		
		for (Labels c : Labels.values()) {
		/*for(int y=0;y<1;y++){
			Labels c=Labels.BOXING;*/
			for(int i=1;i<=MyConstants.TrainVideoCount;i++){
//            for(int i=1;i<=c.getNumberOfVideos();i++){
            	 String videoAddress=MyConstants.dataOfVideosAddress+c.getName()+"/"+c.getName()+"_"+i+".avi";
            	 
            	 MyTools.clearTips();
            	 MyTools.showTips("特征提取中...",1);
            	 MyTools.showTips("进度："+(c.ordinal()*MyConstants.TrainVideoCount+i)+" / "+Labels.getLabelsCount()*MyConstants.TrainVideoCount);
            	 MainWindow.videoPath.setText(MyConstants.S_videoPath+MyConstants.dataOfVideosAddress+c.getName()+"/");
            	 MainWindow.videoName.setText(MyConstants.S_videoName+c.getName()+"_"+i+".avi");
            	 
            	 ExtractVideoFeature ext=new ExtractVideoFeature();
            	 Mat features =ExtractVideoFeature.extract(videoAddress,VideoShow);
            	 MyTools.saveFeaturesToText(features,c,i,outAll,outAll_label);
//         		ext.exe(videoAddress,c,i,outAll,outAll_label,VideoShow);
            }
           
        }
		MyTools.showTips("\n特征提取完毕",1);
		
		outAll.close();
		outAll_label.close();
		
		File f_=MyTools.mkdir(Classifiers.data_hog_Address,Classifiers.data_hog_name);//保存路径
		File f2_=MyTools.mkdir(Classifiers.data_hog_Address,Classifiers.data_hog_label);//保存路径
		
		FileCopy.Copy(f, f_); 
		MyTools.showTips("\n特征数据拷贝完成",1);
		FileCopy.Copy(f2, f2_);		
		MyTools.showTips("\n特征标签拷贝完成",1);
		
		
	}

}
