import { informationType } from "../../types/informationTypes";
import { isInformationType } from "../../types/informationTypes";
import axiosInstance from "../../utils/axiosConfig";

async function getInfo(token: string): Promise<informationType[] | null> {
  try {
    const response = await axiosInstance.get('https://be-fresh.site/api/containers/sensor', {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    });
    
    const temp = response.data.result;
    // console.log(temp)
    if (Array.isArray(temp) && temp.every(isInformationType)) {
      console.log(temp); // 여기서 값이 잘 나옴
      return temp;
    }
  } catch (error) {
    console.error(error);
  }
  return null;
}

export { getInfo }