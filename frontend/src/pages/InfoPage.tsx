import InfoTemp from "../components/templates/infoTemp";
import { getInfo } from "../api/info/infoApi";
import { useEffect, useState } from "react";
import { getAccessToken } from "../utils/tokenUtils";
import { informationType } from "../types/informationTypes";
import { RootState } from "../store/store";
import { useSelector, useDispatch } from "react-redux";
import { deleteFoodId } from "../store/features/InfoSlice";

function InfoPage() {
  const dispatch = useDispatch()
  const token = getAccessToken()
  const [containerInfo, setContainerInfo] = useState<informationType[]>([])
  const foodId = useSelector((state:RootState) => state.foodId.value)
  const [showBackBtn, setShowBackBtn] = useState<boolean>(false)

  const fetchInfo = async (token:string) => {
    if (foodId) {
      setShowBackBtn(true)
      const oneInfo = await getInfo(token, foodId)
      if (oneInfo) {
        setContainerInfo(oneInfo)
        // 뽑아냈으면 없애주자
        dispatch(deleteFoodId())
      }
    } else {
      setShowBackBtn(false)
      const infos = await getInfo(token, null)
      if (infos) {
        setContainerInfo(infos)
      }
    }
  }

  useEffect(() => {
    if (token) {
      fetchInfo(token)
    }
  }, [])

  return (
    <InfoTemp containerInfo={containerInfo} showBackBtn={showBackBtn}/>
  );
}

export default InfoPage;
